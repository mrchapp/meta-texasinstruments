SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP BIOS"
LICENSE = "Texas Instruments"
PR = "r0"

inherit sdotools

SDOVERS = 6_20_02_43

SDOFILE = bios_setuplinux_${SDOVERS}.bin

SDOPATH = "BIOS/${SDOVERS}/exports/${SDOFILE}"

#inherit dfetch
#
#DIRAC_FILE=bios_6_20_02_43
#
#DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/${DIRAC_FILE}"
#DIRAC_PATHCOMPONENT = "${DIRAC_FILE}"
#DIRAC_PATHCOMPONENTS = 4
#
#
#do_stage() {
#	chmod -R +w ${S}/*
#	install -d ${STAGING_BINDIR}/titools/${DIRAC_FILE}
#	cp -a ${S}/* ${STAGING_BINDIR}/titools/${DIRAC_FILE}
#}
