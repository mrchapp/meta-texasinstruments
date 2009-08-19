DESCRIPTION = "Texas Instruments GSM Half-Rate Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-gsmhrenc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/speech/node/hr/enc/... LINUX_RLS_${PV}2RC1%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/hr/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/speech/node/hr/enc

inherit ccasefetch tisocketnode
