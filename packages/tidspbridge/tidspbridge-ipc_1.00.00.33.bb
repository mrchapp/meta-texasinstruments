SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments codegen tools"
LICENSE = "Texas Instruments"
PR = "r0"

inherit dfetch


DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/ipc_1_00_00_33"
DIRAC_PATHCOMPONENT = "ipc_1_00_00_33"
DIRAC_PATHCOMPONENTS = 4


do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/tools/ipc_1_00_00_33
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/tools/
}
