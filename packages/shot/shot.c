#include <gst/gst.h>
#include <glib.h>

/*
For getopt
*/
#include <unistd.h>



#define PRINT_LINE g_printerr("%d\n",__LINE__);
/*
Global variables
*/
GstElement *pipeline = NULL;

gint width;
gint height;
gchar *option;

gint num_buffers;
gchar *output_result_file;
gchar *output_image_file;


gchar *usage = "Usage: %s [-o shot | save] [-b num-buffers] [-w width] [-h height] [-i output-image-file] [-r output-results-file]\n";

static gint
getargs(int argc, char **argv)
{

    int c;

    opterr = 0;

    while ((c = getopt (argc, argv, "w:h:o:b:i:r:")) != -1)
    {
     switch (c)
       {
       case 'r':
         output_result_file = optarg;
         break;
       case 'i':
         output_image_file = optarg;
         break;
       case 'w':
         width = atoi(optarg);
         break;
       case 'h':
         height = atoi(optarg);
         break;
       case 'o':
         option = optarg;
         break;
       case 'b':
         num_buffers = atoi(optarg);
         break;
       case '?':
         g_printerr("Invalid arguments\n");
         g_printerr (usage, argv[0]);
         return 1;
       default:
         abort();
       }
    }
       
    if (!width || !height || !option || !num_buffers || !output_image_file || !output_result_file ||
        (!g_str_equal(option,"shot") && !g_str_equal(option,"save")))
    {
        g_printerr("Missing arguments\n");
        g_printerr (usage, argv[0]);
        return 1;
    }
    else
    {
        
        g_print ("option = %s\nnum-buffers = %d\nwidth = %d\nheight = %d\noutput image file = %s\n output_result_file =%s\n",
               option, num_buffers, width, height, output_image_file, output_result_file);
    }

    return 0;
}


static void
save_result(gdouble start, gdouble end)
{

    gdouble res;
    res = (end - start);

    if (res > 0)
    {
        FILE *file_id;
        file_id = fopen(output_result_file,"a+");
        
        g_print("%dx%d,%f\n", width,height,res);
        
        g_fprintf(file_id, "%s,%dx%d,%f\n", 
            option,width,height,res);
        fclose(file_id);
    }

    return;
  
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
      g_print ("End of stream\n");
      
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
        const GstStructure *structure;
        structure = gst_message_get_structure(msg);
        
        /* Get the starting/ending timestamps */
        if(structure)
        {
            if( g_strcmp0(gst_structure_get_name(structure),"start-point") == 0 )
            {
                g_print("%s\n",gst_structure_get_name(structure));
                gst_structure_get_double(structure,"start-point", &start_point);
            }
            else 
            {
                if( g_strcmp0(gst_structure_get_name(structure),"end-point-shot") == 0 )
                {
                    g_print("%s\n",gst_structure_get_name(structure));
                    gst_structure_get_double(structure,"end-point-shot", &end_point_shot);
                    g_print("done, save results and quit loop\n");
                    save_result(start_point, 
                                (g_str_equal(option,"shot"))? end_point_shot : end_point_save);
                    g_main_loop_quit (loop);
                }
                else if( first_shot &&
                        g_strcmp0(gst_structure_get_name(structure),"end-point-save") == 0 )
                {
                    g_print("%s\n",gst_structure_get_name(structure));
                    gst_structure_get_double(structure,"end-point-save", &end_point_save);
                    first_shot = FALSE;
                }
            }
        }

        break;
    }
    
    default:
      break;
  }

  return TRUE;
}


int
main (int   argc,
      char *argv[])
{
  GMainLoop *loop;

  GstElement  *source, *sink;
  GstBus *bus;
  GError *error;


  /* Get input arguments */
  if (getargs(argc,argv)) 
  {
    return -1;
  }
  
  
  
  /* Initialisation */
  gst_init (&argc, &argv);

  loop = g_main_loop_new (NULL, FALSE);
  
  /* Create a new pipeline */
  gchar *str_pipeline;
  
  
  
  if (g_str_equal(option,"shot"))
  {
      str_pipeline = g_strdup_printf("goocamera num-buffers=%d display-pos-x=50 display-pos-y=200 ! video/x-raw-yuv, format=(fourcc)UYVY, width=%d, height=%d, framerate=0/1 ! fakesink -v",
      num_buffers,
      width,
      height,
      output_image_file,
      "%d");
  }
  else if (g_str_equal(option,"save"))
  {
      str_pipeline  = g_strdup_printf("goocamera num-buffers=%d display-pos-x=50 display-pos-y=200 ! video/x-raw-yuv, format=(fourcc)UYVY, width=%d, height=%d, framerate=0/1  ! gooenc_jpeg thumbnail=128x96  comment=string  !  multifilesink location=%s_%s.jpg",
      num_buffers,
      width,
      height,
      output_image_file,
      "%d");
      
  }
  
  g_print ("Pipeline: %s\n", str_pipeline);
  pipeline = gst_parse_launch(str_pipeline, &error);
  
    
  if (!pipeline) 
  {
    g_printerr ("One element could not be created. Exiting.\n");
    g_free(str_pipeline);
    return -1;
  }

   /* we add a message handler */
  bus = gst_pipeline_get_bus (GST_PIPELINE (pipeline));
  gst_bus_add_watch (bus, bus_call, loop);
  gst_object_unref (bus);

  /* Set the pipeline to "playing" state*/
  gst_element_set_state (pipeline, GST_STATE_PLAYING);


  /* Iterate */
  g_print ("Running...\n");
  g_main_loop_run (loop);
  g_print ("Done...\n");

  /* Out of the main loop, clean up nicely */
  g_print ("Out of the main loop, stopping the pipeline\n");
  gst_element_set_state (GST_ELEMENT (pipeline), GST_STATE_NULL);

  g_print ("Deleting pipeline\n");
  gst_object_unref (GST_OBJECT (pipeline));
  
  g_free(str_pipeline);
  return 0;
}
