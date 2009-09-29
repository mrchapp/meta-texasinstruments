DESCRIPTION = "Texas Instruments NB-AMR Encoder Socket Node."
PR = "r0"
DEPENDS += "tisocketnode-usn tisocketnode-nbamrenc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/... ${SRCREV}%\
	     "

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/node/nbamr/enc"
CCASE_PATHCOMPONENT = "wtbu"
CCASE_PATHCOMPONENTS = "1"

SN_DIR=${S}/OMAPSW_DSP/speech/node/nbamr/enc

inherit tisocketnode

PV = "4.0+cc+${SRCREV}"

XDCARGS = "profile=sn_debug"

XDCPATH += "\
${STAGING_BINDIR}/dspbridge/OMAPSW_DSP/speech/alg/nbamrenc/100_s_gsmamr_e_all_1_xx_yy_tesla;\
"


