SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Framework Components"
LICENSE = "Texas Instruments"
PR = "r0"

inherit sdotools-tar

SDOVERS = 3_00_00_47

SDOFILE = framework_components_${SDOVERS}_eng.tar.gz

SDOPATH = "Framework_Components/${SDOVERS}/exports/${SDOFILE}"

#inherit dfetch
#
#DIRAC_FILE=framework_components_3_00_00_47_eng
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
