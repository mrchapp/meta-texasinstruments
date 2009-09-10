SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments xdc tools"
LICENSE = "Texas Instruments"
PR = "r0"

inherit dfetch

PV_underscr = `echo "${PV}" | sed -e 's/\./_/g'`

DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/xdctools_${PV_underscr}_eng"
DIRAC_PATHCOMPONENT = "xdctools_${PV_underscr}_eng"
DIRAC_PATHCOMPONENTS = 4



do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/tools/xdctools_${PV_underscr}_eng
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/tools/
}
