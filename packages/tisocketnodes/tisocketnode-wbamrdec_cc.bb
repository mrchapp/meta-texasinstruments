DESCRIPTION = "Texas Instruments WB-AMR Decoder Socket Node."
PR = "r1"
DEPENDS += "tisocketnode-usn tisocketnode-wbamrdec-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/... ${SRCREV}%\
	     "

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/wbamr/dec/"
CCASE_PATHCOMPONENT = "wtbu"
CCASE_PATHCOMPONENTS = "1"

SN_DIR = "${S}/OMAPSW_DSP/speech/node/wbamr/dec"

inherit tisocketnode

PV = "4.0+cc+${SRCREV}"

XDCARGS = "profile=sn_debug"

XDCPATH += "\
${STAGING_BINDIR}/dspbridge/OMAPSW_DSP/speech/alg/wbamrdec/100_s_g7222_d_all_1_xx_yy_tesla;\
"

