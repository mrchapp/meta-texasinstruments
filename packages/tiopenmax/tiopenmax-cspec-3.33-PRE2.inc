# Integration config spec for L23.I3.7-PRE2
CCASE_SPEC = "\
	${@base_contains("DISTRO_FEATURES", "testpatterns", "", "element patterns /main/0", d)}%\

	# OMX Audio%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/... ACT_LINUX-MMAUDIO_PREREL_I3.7.2%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/... LINUX-MMAUDIO_RLS_3.33%\
	
	# OMX Video%\
	element /vobs/wtbu/OMAPSW_MPU/linux/video/src/openmax_il/video_decode_ittiam/...  LINUX-MMVIDEO_RLS_3.34-PRE1%\
        element /vobs/wtbu/OMAPSW_MPU/linux/video/src/openmax_il/mms_camera/... LINUX-MMVIDEO_RLS_3.33-PRE2%\
 	element /vobs/wtbu/OMAPSW_MPU/linux/video/... LINUX-MMVIDEO_RLS_3.33%\
	
	# OMX Image%\
	element /vobs/wtbu/OMAPSW_MPU/linux/image/... LINUX-MMIMAGE_RLS_3.33%\
	
	# LCML & core%\
	element /vobs/wtbu/OMAPSW_MPU/linux/system/... ACT_LINUX-MMSYSTEM_PREREL_I3.7.2%\
	element /vobs/wtbu/OMAPSW_MPU/linux/system/... LINUX-MMSYSTEM_RLS_3.33%\
	
	# OMX Application%\
	element /vobs/wtbu/OMAPSW_MPU/linux/application/src/openmax_il/video_capture/... LINUX-MMAPPLICATION_RLS_3.33-PRE2%\ 	
	element /vobs/wtbu/OMAPSW_MPU/linux/application/... LINUX-MMAPPLICATION_RLS_3.33%\
	
	# OMX INST2 utilities%\
	element /vobs/wtbu/OMAPSW_MPU/linux/utilities/... LINUX-MMUTILS_RLS_3.33%\
	element /vobs/wtbu/OMAPSW_MPU/linux/utilities/... COMPONENT_ROOT%\

	# ROOT folder & Make files%\
	element /vobs/wtbu/OMAPSW_MPU/linux/... LINUX-MMROOT_RLS_3.23%\
	# special hack needed because root of vob is not labeled:%\
	element /vobs/wtbu/OMAPSW_MPU /main/LATEST%\
	# don't pick up anything that is not labeled%\
	element * /main/0%\
	"

# Uncomment to Disable resource manager support
# export OMX_RESOURCEMANAGER_DISABLE=1


