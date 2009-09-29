PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MP3 Decoder Socket Node Codec."
PR = "r0"

CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_DSP/... ${SRCREV}%\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/alg/mp3dec/100_a_mp3_d_1_xx_yy_tesla/omap4_l3"
CCASE_PATHCOMPONENT = "wtbu"
CCASE_PATHCOMPONENTS = "1"

inherit ccasefetch

PV = "4.0+cc+${SRCREV}"

do_stage() {
    install -d ${STAGING_BINDIR}/dspbridge/OMAPSW_DSP
    cp -a ${S}/OMAPSW_DSP/* ${STAGING_BINDIR}/dspbridge/OMAPSW_DSP
}
