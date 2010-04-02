DESCRIPTION = "Texas Instruments MPEG4 720p Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-mpeg4-xdm720penc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/video/node/mpeg4_720PX/... DSP-MM-TII_RLS_${PV}%\
	      element * COMPONENT_ROOT%\
	      element -directory /vobs/wtbu/CSSD_MM_Releases/... /main/LATEST%\
	      "

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/mpeg4_720PX/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/video/node/mpeg4_720PX/enc

inherit ccasefetch tisocketnode