DESCRIPTION = "Texas Instruments GSM Half-Rate Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-gsmhrdec-codec"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/hr/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/hr/dec

inherit ccasefetch tisocketnode
