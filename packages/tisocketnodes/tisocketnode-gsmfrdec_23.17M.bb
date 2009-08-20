DESCRIPTION = "Texas Instruments GSM Full-Rate Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-gsmfrdec-codec"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/fr/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR = ${S}/speech/node/fr/dec

inherit ccasefetch tisocketnode
