DESCRIPTION = "Texas Instruments MPEG4 AAC Decoder Socket Node."
PR = "r1"
DEPENDS += "tisocketnode-mpeg4aacdec-codec tisocketnode-usn"

CCASE_SPEC = "%\
          element /vobs/wtbu/OMAPSW_DSP/... ${SRCREV}%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/mpeg4aac/dec"
CCASE_PATHCOMPONENT = "wtbu"
CCASE_PATHCOMPONENTS = "1"

inherit tisocketnode

PV = "4.0+cc+${SRCREV}"

SN_DIR="${S}/OMAPSW_DSP/audio/node/mpeg4aac/dec"

XDCARGS = "profile=sn_debug"

XDCPATH += "\
${STAGING_BINDIR}/dspbridge/OMAPSW_DSP/audio/alg/aacdec/100_a_aac_d_he_1_xx_yy_tesla;\
"


