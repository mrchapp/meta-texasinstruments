DESCRIPTION = "Texas Instruments PCM Encoder Socket Node."
PR = "r0"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/pcm/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/audio/node/pcm/enc

inherit ccasefetch tisocketnode
