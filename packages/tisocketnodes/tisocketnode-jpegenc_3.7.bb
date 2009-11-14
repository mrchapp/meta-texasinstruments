DESCRIPTION = "Texas Instruments JPEG Encoder Socket Node."
PR = "r1"
DEPENDS += "tisocketnode-jpegenc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/image/node/jpeg/enc/... DSP-MM-TII_RLS_${PV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/image/node/jpeg/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/image/node/jpeg/enc

inherit ccasefetch tisocketnode

