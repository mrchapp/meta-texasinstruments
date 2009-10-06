SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments IPC library"
LICENSE = "Texas Instruments"
PR = "r1"

#inherit sdotools-tar
#
#SDOVERS = 1_00_03_54
#
#SDOFILE = ipc_${SDOVERS}_eng.tar.gz
#
#SDOPATH = "IPC/${SDOVERS}/exports/${SDOFILE}"

inherit dfetch

DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/ipc_1_00_03_54_eng"
DIRAC_PATHCOMPONENT = "ipc_1_00_03_54_eng"
DIRAC_PATHCOMPONENTS = 4 

do_stage() {
	chmod -R +rw ${S}/*
	install -d ${STAGING_BINDIR}/titools/ipc_1_00_03_54_eng
	cp -a ${S}/* ${STAGING_BINDIR}/titools/	
}


