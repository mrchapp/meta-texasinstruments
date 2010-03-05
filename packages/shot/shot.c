#include <gst/gst.h>
#include <glib.h>

/*
For getopt
*/
#include <unistd.h>


typedef enum {
  INVALID_TEST = 0,
  STANDBY_TO_FIRST_SHOT,
  SHOT_TO_SHOT,
  SHOT_TO_SAVE
  } ShotTestCases;

/*
Global variables
*/
static GstElement *pipeline;
static GHashTable *timestamps;
ShotTestCases option=INVALID_TEST;


/*
Functions
*/

/*
Get input arguments
*/
static ShotTestCases
get_input_arguments(
  int argc,
  char **argv,
  ShotTestCases *option,
  gint *width,
  gint *height,
  gint *num_buffers,
  gchar **output_result_file,
  gchar **output_image_file)
{

    static gchar *usage = "Usage: %s [-o 0=STANDBY_TO_FIRST_SHOT | 1=SHOT_TO_SHOT | 2=SHOT_TO_SAVE] [-b num-buffers] [-w width] [-h height] [-i output-image-file] [-r output-results-file]\n";
    int c;

    opterr = 0;

    while ((c = getopt (argc, argv, "w:h:o:b:i:r:d")) != -1)
    {
     switch (c)
       {
       case 'r':
         *output_result_file = optarg;
         break;
       case 'i':
         *output_image_file = optarg;
         break;
       case 'w':
         *width = atoi(optarg);
         break;
       case 'h':
         *height = atoi(optarg);
         break;
       case 'o':
         switch(atoi(optarg))
         {
           case 0:
             *option = STANDBY_TO_FIRST_SHOT;
             break;
           case 1:
             *option = SHOT_TO_SHOT;
             break;
           case 2:
             *option = SHOT_TO_SAVE;
             break;
           default:
             *option = INVALID_TEST;
             break;
          }
         break;
       case 'b':
         *num_buffers = atoi(optarg);
         break;
       case '?':
         g_printerr("Invalid arguments\n");
         g_printerr (usage, argv[0]);
         return INVALID_TEST;
       default:
         abort();
       }
    }

/*
    necessary parameters
*/
    if( *option == INVALID_TEST )
    {
      g_printerr("Missing arguments [-o option]\n");
      g_printerr (usage, argv[0]);
        return INVALID_TEST;
    }

    return *option;
}

static gchar *
get_timestamp_string(GstStateChange transition)
{
  return g_strdup_printf("camera_transition_%d",transition);
}

static gdouble
get_state_transition_timestamp_double(GstStateChange transition)
{
  gchar *str;
  gdouble timestamp;
  gpointer value;

  str = get_timestamp_string(transition);
  value = (gchar *)g_hash_table_lookup(timestamps,str);
  g_return_val_if_fail(value != NULL, 0);
  timestamp = atof(value);
  g_free(str);
  return timestamp;
}

static gdouble
get_timestamp_double (gchar *str)
{
  gdouble timestamp;
  gpointer value;

  value = (gchar *)g_hash_table_lookup(timestamps,str);
  g_return_val_if_fail(value != NULL, 0);
  timestamp = atof(value);
  return timestamp;
}



static gint
print_results(ShotTestCases option, gint width, gint height, gchar *output_result_file)
{
    gdouble t0 = 0;
    gdouble t1 = 0;
    gdouble delta = 0;
    gchar *str_test_name = NULL;

    switch(option)
    {
        case STANDBY_TO_FIRST_SHOT:
        {
		t0 = get_state_transition_timestamp_double(GST_STATE_CHANGE_NULL_TO_READY);
		t1 = get_state_transition_timestamp_double(GST_STATE_CHANGE_PAUSED_TO_PLAYING);
		str_test_name = g_strdup_printf("STANDBY_TO_FIRST_SHOT");
		break;
        }
        case SHOT_TO_SHOT:
	{
		t0 = get_state_transition_timestamp_double(GST_STATE_CHANGE_PAUSED_TO_PLAYING);
		t1 = get_state_transition_timestamp_double(GST_STATE_CHANGE_PLAYING_TO_PAUSED);
		str_test_name = g_strdup_printf("SHOT_TO_SHOT");
		break;
        }
        case SHOT_TO_SAVE:
	{
		gpointer value;
		t0 = get_state_transition_timestamp_double(GST_STATE_CHANGE_PAUSED_TO_PLAYING);
		t1 = get_timestamp_double("filtervpp_chain");
	        str_test_name = g_strdup_printf("SHOT_TO_SAVE");
		break;
        }
        default:
          break;
    }

    g_return_val_if_fail(t0 > 0, -1);
    g_return_val_if_fail(t1 > 0, -1);
    g_return_val_if_fail(t1 > t0, -1);
    delta = t1 - t0;

    FILE *file_id;
    file_id = fopen(output_result_file,"a+");

    g_print("%s,%dx%d,%f\n",
        str_test_name,width,height,delta);

    g_fprintf(file_id, "%s,%dx%d,%f\n",
        str_test_name,width,height,delta);


    g_print("Result appended in file %s\n", output_result_file);

    fclose(file_id);
    g_free(str_test_name);

    return 0;
}
  
static gboolean
endpoint_reached ()
{
	gchar *str = NULL;
	gboolean retval = FALSE;

	if (option == SHOT_TO_SAVE)
	  str = g_strdup_printf("%s","filtervpp_chain");
	else
	  str = g_strdup_printf("camera_transition_%d",GST_STATE_CHANGE_PLAYING_TO_PAUSED);

	retval = g_hash_table_lookup(timestamps, str) != NULL;
	g_free (str);

	return retval;
}


static gboolean
message_comes_from_component(gchar *component_name, GstMessage *msg)
{
  return g_str_has_prefix(gst_object_get_name(GST_MESSAGE_SRC(msg)),component_name);
}

static gboolean
message_has_structure(GstMessage *msg)
{
  return (gst_message_get_structure(msg) != NULL);
}

static gboolean
bus_call (GstBus     *bus,
          GstMessage *msg,
          gpointer    data)
{
  GMainLoop *loop = (GMainLoop *) data;
  static gdouble start_point = -1;
  static gdouble end_point_shot = -1;
  static gdouble end_point_save = -1;
  static gboolean first_shot = TRUE;

  switch (GST_MESSAGE_TYPE (msg)) 
  {

    case GST_MESSAGE_EOS:
    {
      gst_element_set_state (pipeline, GST_STATE_PAUSED);
      break;
    }

    case GST_MESSAGE_ERROR: 
    {
      gchar  *debug;
      GError *error;

      gst_message_parse_error (msg, &error, &debug);
      g_free (debug);

      g_printerr ("Error: %s\n", error->message);
      g_error_free (error);
                 
      g_main_loop_quit (loop);
      break;
    }
    
    case GST_MESSAGE_ELEMENT: 
    {
        if(message_has_structure(msg))
        {
          const GstStructure *structure;
          const gchar *name;
          const gchar *timestamp;

          structure = gst_message_get_structure(msg);
          name = gst_structure_get_name(structure);
          timestamp = gst_structure_get_string(structure,"timestamp");


          if (timestamps == NULL)
          { /* create the hash which will contain the timestamps */
              timestamps = g_hash_table_new_full(g_str_hash,g_str_equal,g_free,g_free);
          }

          { /*insert the camera_transition_xx/timestamp comming from the component */
            gpointer value;
            value = g_hash_table_lookup(timestamps,name);

            /* Just insert new keys, do not overwrite */
            if (value == NULL)
            {
              g_hash_table_insert(timestamps, g_strdup(name), g_strdup(timestamp));
            }
          }

          /* Check if we are done */
          if (endpoint_reached ())
          {
              g_main_loop_quit (loop);
          }
        }

        break;
    }
    
    default:
      break;
  }

  return TRUE;
}

static gint
run_pipeline(int argc, char *argv[], gchar *str_pipeline)
{
  GMainLoop *loop;
  GstBus *bus;
  GError *error;
  
  /* Initialisation */
  gst_init (&argc, &argv);

  loop = g_main_loop_new (NULL, FALSE);
  
  pipeline = gst_parse_launch(str_pipeline, NULL);
  
    
  g_return_val_if_fail(pipeline != NULL,-1);

  /* we add a message handler */
  bus = gst_pipeline_get_bus (GST_PIPELINE (pipeline));
  gst_bus_add_watch (bus, bus_call, loop);
  gst_object_unref (bus);

  /* Set the pipeline to "playing" state*/
  gst_element_set_state (pipeline, GST_STATE_PLAYING);


  /* Iterate */
  g_main_loop_run (loop);


  /* Out of the main loop, clean up nicely */

  gst_object_unref (GST_OBJECT (pipeline));
  
  return 0;
}


gchar *create_str_pipeline(ShotTestCases option, gint width, gint height, gint num_buffers, gchar *output_image_file)
{
    gchar *str_pipeline;
    gchar *str_goocamera;
    gchar *str_cap;

    str_goocamera = g_strdup_printf("goocamera num-buffers=%d display-pos-x=50 display-pos-y=200", num_buffers);
    str_cap = g_strdup_printf("video/x-raw-yuv, format=(fourcc)UYVY, width=%d, height=%d, framerate=0/1",width, height);

    switch(option)
    {
      case STANDBY_TO_FIRST_SHOT:
      case SHOT_TO_SHOT:
      {
          str_pipeline = g_strdup_printf("%s ! %s ! fakesink",
                                         str_goocamera,
                                         str_cap);
          break;
        }
      case SHOT_TO_SAVE:
        {
          gchar *str_gooenc_jpeg;
          gchar *str_multifilesink;
          gchar *str_goofilter_vpp;
          gchar *str_vpp_cap;
          str_goofilter_vpp = g_strdup_printf("goofilter_vpp rotation=90");
          str_gooenc_jpeg   = g_strdup_printf("gooenc_jpeg thumbnail=128x96  comment=string");
          str_multifilesink = g_strdup_printf("filesink name=filesink0 location=%s_%s.jpg",
                                              output_image_file,
                                              "%d");
          str_vpp_cap = g_strdup_printf("video/x-raw-yuv, format=(fourcc)UYVY, width=%d, height=%d, framerate=0/1",height,width);

          str_pipeline = g_strdup_printf("%s ! %s ! %s ! %s ! %s",
                                        str_goocamera,
                                        str_cap,
                                        str_goofilter_vpp,
                                        str_gooenc_jpeg,
                                        str_multifilesink);
          g_free(str_gooenc_jpeg);
          g_free(str_multifilesink);
          g_free(str_goofilter_vpp);
          g_free(str_vpp_cap);
          break;
        }
      default:
        {
          str_pipeline = NULL;
          break;
        }
    }

    g_free(str_goocamera);
    g_free(str_cap);

    g_print("Pipeline to be run:\n%s\n",str_pipeline);
    return str_pipeline;
}


int
main (int   argc,
      char *argv[])
{

    gchar *str_pipeline;

/*
    Default values
*/
    gchar *output_result_file ="shot.txt";
    gint width=640;
    gint height=480;
    gint num_buffers=1;
    gchar *output_image_file="test";

    /* Get input arguments */
    if(get_input_arguments(argc,
                           argv,
                           &option,
                           &width,
                           &height,
                           &num_buffers,
                           &output_result_file,
                           &output_image_file) == INVALID_TEST)
    {
     return -1;
    }

    {/* Create the pipeline string */
        str_pipeline = create_str_pipeline(option,width,height,num_buffers,output_image_file);
        g_return_val_if_fail(str_pipeline != NULL, -1);
    }

    {/* Play the pipeline */
        g_assert( run_pipeline(argc,argv,str_pipeline) == 0);
    }

    {/* print the results */
      print_results(option, width, height, output_result_file);
    }

    { /* Cleaning global variables */
      g_hash_table_destroy(timestamps);
      g_free(str_pipeline);
    }


    return 0;
}
