PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPEG4 AAC Encoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"


CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_DSP/... DSP-MM-TESLA_RLS_4.1%\
	element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/alg/aacenc/100_a_aac_e_1_12_000_tesla/omap4_he"
CCASE_PATHCOMPONENT = "omap4_he"
CCASE_PATHCOMPONENTS = "7"

inherit ccasefetch

do_stage() {

	install -d ${STAGING_BINDIR}/dspbridge/audio/alg/aacenc/omap4_he
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/audio/alg/aacenc/omap4_he
}

