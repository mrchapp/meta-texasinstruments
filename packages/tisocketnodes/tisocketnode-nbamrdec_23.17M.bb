DESCRIPTION = "Texas Instruments NB-AMR Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-nbamrdec-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/speech/node/nbamr/dec/... LINUX_RLS_${PV}0%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/nbamr/dec/"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/nbamr/dec

inherit ccasefetch tisocketnode
