DESCRIPTION = "Texas Instruments PCM Decoder Socket Node."
PR = "r0"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/pcm/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/audio/node/pcm/dec

inherit ccasefetch tisocketnode
