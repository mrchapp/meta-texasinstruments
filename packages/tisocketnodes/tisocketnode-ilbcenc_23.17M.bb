DESCRIPTION = "Texas Instruments iLBC Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-ilbcenc-codec"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/iLBC/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/iLBC/enc

inherit ccasefetch tisocketnode
