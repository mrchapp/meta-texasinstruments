SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP BIOS"
LICENSE = "Texas Instruments"
PR = "r2"

#inherit sdotools
#
#SDOVERS = 6_20_02_43
#
#SDOFILE = bios_setuplinux_${SDOVERS}.bin
#
#SDOPATH = "BIOS/${SDOVERS}/exports/${SDOFILE}"

inherit dfetch

DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/bios_6_20_02_43/bios_6_20_02_43"
DIRAC_PATHCOMPONENT = "bios_6_20_02_43"
DIRAC_PATHCOMPONENTS = 5

do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/titools/bios_6_20_02_43
	cp -a ${S}/* ${STAGING_BINDIR}/titools/	
}
