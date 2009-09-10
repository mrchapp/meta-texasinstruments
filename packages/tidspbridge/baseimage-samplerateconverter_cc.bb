PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage Audio Algorithm SampleRateConverter."
LICENSE = "LGPL"
PR = "r0"

inherit ccasefetch

PV = "4.0+cc+${SRCREV}"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/audio/alg/SampleRateConverter/... ${SRCREV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/alg/SampleRateConverter"
CCASE_PATHCOMPONENT = "SampleRateConverter"
CCASE_PATHCOMPONENTS = "5"


do_compile() {
}

do_stage() {
    chmod -R +w ${S}/*
    install -d ${STAGING_BINDIR}/dspbridge/audio/alg/SampleRateConverter
    cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/audio/alg/SampleRateConverter
}
