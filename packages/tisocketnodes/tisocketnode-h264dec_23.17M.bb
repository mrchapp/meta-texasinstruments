DESCRIPTION = "Texas Instruments H264 Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-h264dec-codec"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/video/node/h264/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/video/node/h264/dec

inherit ccasefetch tisocketnode
