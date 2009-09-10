PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPEG4 AAC Decoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"


CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_DSP/... DSP-MM-TESLA_RLS_4.1%\
	element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/alg/aacdec/100_aac_d_he_1_06_04_tesla/omap4_he_mpeg4"
CCASE_PATHCOMPONENT = "omap4_he_mpeg4"
CCASE_PATHCOMPONENTS = "7"

inherit ccasefetch

do_stage() {

	install -d ${STAGING_BINDIR}/dspbridge/audio/alg/aacdec/omap4_he_mpeg4
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/audio/alg/aacdec/omap4_he_mpeg4
}
