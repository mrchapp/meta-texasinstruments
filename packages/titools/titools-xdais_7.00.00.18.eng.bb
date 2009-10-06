SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments OSAL"
LICENSE = "Texas Instruments"
PR = "r1"

#inherit sdotools-tar
#
#SDOVERS = 7_00_00_18
#
#SDOFILE = xdais_${SDOVERS}_eng.tar.gz
#
#SDOPATH = "XDAIS/${SDOVERS}/exports/${SDOFILE}"

inherit dfetch

DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/xdais_7_00_00_18_eng"
DIRAC_PATHCOMPONENT = "xdais_7_00_00_18_eng"
DIRAC_PATHCOMPONENTS = 4 

do_stage() {
	chmod -R +rw ${S}/*
	install -d ${STAGING_BINDIR}/titools/xdais_7_00_00_18_eng
	cp -a ${S}/* ${STAGING_BINDIR}/titools/	
}
