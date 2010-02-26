DESCRIPTION = "Texas Instruments H264 Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-h264enc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/video/node/h264/enc/... DSP-MM-TII_RLS_${PV}%\
	      element * COMPONENT_ROOT%\
	      element -directory /vobs/wtbu/CSSD_MM_Releases/... /main/LATEST%\
	      "

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/h264/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/video/node/h264/enc

inherit ccasefetch tisocketnode
