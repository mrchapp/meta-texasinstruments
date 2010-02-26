DESCRIPTION = "Texas Instruments IMA-ADPCM Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-ima-adpcmdec-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/audio/node/ima-adpcm/dec/... DSP-MM-TII_RLS_${PV}%\
	      element * COMPONENT_ROOT%\
	      element -directory /vobs/wtbu/CSSD_MM_Releases/... /main/LATEST%\
	      "

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/ima-adpcm/dec"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/audio/node/ima-adpcm/dec

inherit ccasefetch tisocketnode
