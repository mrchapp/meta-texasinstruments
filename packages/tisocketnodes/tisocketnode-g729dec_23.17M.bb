DESCRIPTION = "Texas Instruments G729 Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-g729dec-codec"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/g729/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/g729/dec

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode
