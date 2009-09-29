DESCRIPTION = "Texas Instruments MP3 Decoder Socket Node."
PR = "r1"
DEPENDS += "tisocketnode-usn tisocketnode-mp3-codec"

inherit tisocketnode

PV = "4.0+cc+${SRCREV}"

CCASE_SPEC = "element /vobs/wtbu/OMAPSW_DSP/... ${SRCREV}%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/mp3/dec"
CCASE_PATHCOMPONENT = "wtbu"
CCASE_PATHCOMPONENTS = "1"

#/vobs/wtbu/OMAPSW_DSP/audio/alg/mp3dec/100_a_mp3_d_1_xx_yy_tesla

SN_DIR="${S}/OMAPSW_DSP/audio/node/mp3/dec"

XDCARGS = "profile=sn_debug"

XDCPATH += "\
${STAGING_BINDIR}/dspbridge/OMAPSW_DSP/audio/alg/mp3dec/100_a_mp3_d_1_xx_yy_tesla;\
"
