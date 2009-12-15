DESCRIPTION = "Texas Instruments WB-AMR Encoder Socket Node."
PR = "r1"
DEPENDS += "tisocketnode-usn tisocketnode-wbamrenc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/... ${SRCREV}%\
	     "

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/wbamr/enc"
CCASE_PATHCOMPONENT = "wtbu"
CCASE_PATHCOMPONENTS = "1"

SN_DIR=${S}/OMAPSW_DSP/speech/node/wbamr/enc

inherit tisocketnode

PV = "4.0+cc+${SRCREV}"

XDCARGS = "profile=sn_debug"

XDCPATH += "\
${STAGING_BINDIR}/dspbridge/OMAPSW_DSP/speech/alg/wbamrenc/100_s_g7222_e_all_1_xx_yy_tesla;\
"


