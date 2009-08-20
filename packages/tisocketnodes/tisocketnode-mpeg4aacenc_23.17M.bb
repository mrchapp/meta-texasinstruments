DESCRIPTION = "Texas Instruments MPEG4 AAC Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-mpeg4aacenc-codec"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/mpeg4aac/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/audio/node/mpeg4aac/enc

inherit ccasefetch tisocketnode
