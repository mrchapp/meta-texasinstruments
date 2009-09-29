PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPEG4 AAC Decoder Socket Node Codec."
PR = "r0"

CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_DSP/... ${SRCREV}%\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/alg/aacdec/100_a_aac_d_he_1_xx_yy_tesla"
CCASE_PATHCOMPONENT = "wtbu"
CCASE_PATHCOMPONENTS = "1"

inherit ccasefetch

PV = "4.0+cc+${SRCREV}"

do_stage() {
    install -d ${STAGING_BINDIR}/dspbridge
    cp -a ${S}/* ${STAGING_BINDIR}/dspbridge
}
