DESCRIPTION = "Texas Instruments MPEG4 Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-mpeg4dec-codec"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/mpeg4/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/video/node/mpeg4/dec

inherit ccasefetch tisocketnode
