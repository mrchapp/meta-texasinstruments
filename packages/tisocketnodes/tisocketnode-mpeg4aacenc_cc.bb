PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPEG4 AAC Encoder Socket Node."
PR = "r1"
DEPENDS += "tisocketnode-mpeg4aacenc-codec tisocketnode-usn"

CCASE_SPEC = "%\
          element /vobs/wtbu/OMAPSW_DSP/... ${SRCREV}%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/mpeg4aac/enc"
CCASE_PATHCOMPONENT = "wtbu"
CCASE_PATHCOMPONENTS = "1"

inherit tisocketnode

PV = "4.0+cc+${SRCREV}"

SN_DIR="${S}/OMAPSW_DSP/audio/node/mpeg4aac/enc"

XDCARGS = "profile=sn_debug"

XDCPATH += "\
${STAGING_BINDIR}/dspbridge/OMAPSW_DSP/audio/alg/aacenc/100_a_aac_e_1_xx_yy_tesla;\
"


