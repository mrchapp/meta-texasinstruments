DESCRIPTION = "Texas Instruments NB-AMR Decoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-usn tisocketnode-nbamrdec-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/... ${SRCREV}%\
	     "

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/nbamr/dec/"
CCASE_PATHCOMPONENT = "wtbu"
CCASE_PATHCOMPONENTS = "1"

SN_DIR = "${S}/OMAPSW_DSP/speech/node/nbamr/dec"

inherit tisocketnode

PV = "4.0+cc+${SRCREV}"

XDCARGS = "profile=sn_debug"

XDCPATH += "\
${STAGING_BINDIR}/dspbridge/OMAPSW_DSP/speech/alg/nbamrdec/100_s_gsmamr_d_all_1_xx_yy_tesla;\
"


