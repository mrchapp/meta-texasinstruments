DESCRIPTION = "Texas Instruments MPEG4 AAC Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-mpeg4aacenc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/audio/node/mpeg4aac/enc/... LINUX_RLS_${PV}2RC1%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/mpeg4aac/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/audio/node/mpeg4aac/enc

inherit ccasefetch tisocketnode
