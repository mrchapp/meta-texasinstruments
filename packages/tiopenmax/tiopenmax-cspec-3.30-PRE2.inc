# Integration config spec for L23.I3.4
CCASE_SPEC = "\
	${@base_contains("DISTRO_FEATURES", "testpatterns", "", "element patterns /main/0", d)}%\

	# OMX Audio%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/... LINUX-MMAUDIO_RLS_3.30%\
	
	# OMX Video%\
	element /vobs/wtbu/OMAPSW_MPU/linux/video/src/openmax_il/video_decode/... ACT_MMVIDEO-PREREL-I3.5.1%\
	element /vobs/wtbu/OMAPSW_MPU/linux/video/src/openmax_il/video_encode/... ACT_MMVIDEO-PREREL-I3.5.1%\
	element /vobs/wtbu/OMAPSW_MPU/linux/video/src/openmax_il/mms_camera/... ACT_MMVIDEO-PREREL-I3.5.1%\
	element /vobs/wtbu/OMAPSW_MPU/linux/video/src/openmax_il/prepost_processor/... ACT_MMVIDEO-PREREL-I3.5.1%\
 	element /vobs/wtbu/OMAPSW_MPU/linux/video/... LINUX-MMVIDEO_RLS_3.29%\
	
	# OMX Image%\
	element /vobs/wtbu/OMAPSW_MPU/linux/image/... ACT_MMIMAGE-PREREL-I3.5.1%\
	element /vobs/wtbu/OMAPSW_MPU/linux/image/... LINUX-MMIMAGE_RLS_3.29%\
	
	# LCML & core%\
	element /vobs/wtbu/OMAPSW_MPU/linux/system/... LINUX-MMSYSTEM_RLS_3.30%\
	
	# OMX Application%\
	element /vobs/wtbu/OMAPSW_MPU/linux/application/src/openmax_il/image_display/... ACT_MMAPPLICATION-PREREL-I3.5.1%\
	element /vobs/wtbu/OMAPSW_MPU/linux/application/... LINUX-MMAPPLICATION_RLS_3.29%\
	
	# OMX INST2 utilities%\
	element /vobs/wtbu/OMAPSW_MPU/linux/utilities/src/inst2/... LINUX-MMUTILS_RLS_3.02.04%\
	
	# ROOT folder & Make files%\
	element /vobs/wtbu/OMAPSW_MPU/linux/... LINUX-MMROOT_RLS_3.21%\
	# special hack needed because root of vob is not labeled:%\
	element /vobs/wtbu/OMAPSW_MPU /main/LATEST%\
	# don't pick up anything that is not labeled%\
	element * /main/0%\
	"

# Uncomment to Disable resource manager support
# export OMX_RESOURCEMANAGER_DISABLE=1


