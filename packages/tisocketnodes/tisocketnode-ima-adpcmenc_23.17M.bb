DESCRIPTION = "Texas Instruments IMA-ADPCM Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-ima-adpcmenc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/audio/node/ima-adpcm/enc/... LINUX_RLS_${PV}0%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/ima-adpcm/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/audio/node/ima-adpcm/enc

inherit ccasefetch tisocketnode
