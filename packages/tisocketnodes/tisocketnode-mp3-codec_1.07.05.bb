DESCRIPTION = "Texas Instruments MP3 Decoder Socket Node Codec."
PR = "r0"
DEPENDS = "baseimage"


CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_DSP/... DSP-MM-TESLA_RLS_4.1%\
	element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/alg/mp3dec/100_a_mp3_d_1_07_05_tesla/omap4_l3"
CCASE_PATHCOMPONENT = "omap4_l3"
CCASE_PATHCOMPONENTS = "7"

inherit ccasefetch

do_stage() {


	install -d ${STAGING_BINDIR}/dspbridge/audio/alg/mp3dec/omap4_l3
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/audio/alg/mp3dec/omap4_l3
}


