DESCRIPTION = "Texas Instruments NB-AMR Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-nbamrenc-codec"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/nbamr/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/nbamr/enc

inherit ccasefetch tisocketnode
