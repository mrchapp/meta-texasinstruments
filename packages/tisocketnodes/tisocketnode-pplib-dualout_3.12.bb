DESCRIPTION = "Texas Instruments JPEG Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-jpegenc"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/video/... DSP-MM-TII_RLS_${PV}%\
	      element * COMPONENT_ROOT%\
	      element -directory /vobs/wtbu/CSSD_MM_Releases/... /main/LATEST%\
	      "

CCASE_PATHFETCH = " \
	/vobs/wtbu/OMAPSW_DSP/video/lib/pplib_dualout \
	/vobs/wtbu/OMAPSW_DSP/video/alg/vgpop \
	"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/video/lib/pplib_dualout

inherit ccasefetch tisocketnode

