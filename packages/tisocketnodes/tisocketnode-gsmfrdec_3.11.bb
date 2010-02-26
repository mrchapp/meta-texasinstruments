DESCRIPTION = "Texas Instruments GSM Full-Rate Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-gsmfrdec-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/speech/node/fr/dec/... DSP-MM-TII_RLS_${PV}%\
	      element * COMPONENT_ROOT%\
	      element -directory /vobs/wtbu/CSSD_MM_Releases/... /main/LATEST%\
	      "

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/fr/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR = ${S}/speech/node/fr/dec

inherit ccasefetch tisocketnode
