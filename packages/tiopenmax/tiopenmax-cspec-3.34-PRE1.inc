# Integration config spec for L23.I3.8-PRE0
CCASE_SPEC = "\
	${@base_contains("DISTRO_FEATURES", "testpatterns", "", "element patterns /main/0", d)}%\

	# OMX Audio%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/... ACT_MMAUDIO-PREREL-I3.8.1%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/... ACT_MMAUDIO-PREREL-I3.8.0%\
	
	# OMX Video%\
	element /vobs/wtbu/OMAPSW_MPU/linux/video/... ACT_LINUX-MMVIDEO-PREREL-I3.8.2%\
	element /vobs/wtbu/OMAPSW_MPU/linux/video/... ACT_LINUX-MMVIDEO-PREREL-I3.8.1%\

	# OMX Image%\
	element /vobs/wtbu/OMAPSW_MPU/linux/image/... LINUX-MMIMAGE_RLS_3.34%\
	
	# LCML & core%\
	element /vobs/wtbu/OMAPSW_MPU/linux/system/... ACT_MMSYSTEM-PREREL-I3.8.1%\
	element /vobs/wtbu/OMAPSW_MPU/linux/system/... ACT_MMSYSTEM-PREREL-I3.8.0%\
	
	# OMX Application%\
	element /vobs/wtbu/OMAPSW_MPU/linux/application/... ACT_LINUX-MMAPPLICATION-PRERLS-I3.8.1%\ 
	element /vobs/wtbu/OMAPSW_MPU/linux/application/... LINUX-MMAPPLICATION_RLS_3.34%\ 
	
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


