SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments OSAL"
LICENSE = "Texas Instruments"
PR = "r1"

#inherit sdotools-tar
#
#SDOVERS = 1_00_00_36
#
#SDOFILE = osal_${SDOVERS}.tar.gz
#
#SDOPATH = "OSAL/${SDOVERS}/exports/${SDOFILE}"

inherit dfetch

DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/osal_1_00_00_36"
DIRAC_PATHCOMPONENT = "osal_1_00_00_36"
DIRAC_PATHCOMPONENTS = 4 

do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/titools/osal_1_00_00_36
	cp -a ${S}/* ${STAGING_BINDIR}/titools/	
}



