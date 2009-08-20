DESCRIPTION = "Texas Instruments MPEG4 720p Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-mpeg4-720pdec-codec"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/mpeg4_ari/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/video/node/mpeg4_ari/dec

inherit ccasefetch tisocketnode
