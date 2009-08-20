DESCRIPTION = "Texas Instruments iLBC Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-ilbcdec-codec"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/iLBC/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/iLBC/dec

inherit ccasefetch tisocketnode
