SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments xdc tools"
LICENSE = "Texas Instruments"
PR = "r0"

inherit dfetch


DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/xdctools_3_15_00_28"
DIRAC_PATHCOMPONENT = "xdctools_3_15_00_28"
DIRAC_PATHCOMPONENTS = 4

#DIRAC_PATHFETCH = "/data/EVM_filesystems/x0104108/sque/xdctools_3_15_00_28"
#DIRAC_PATHCOMPONENT = "xdctools_3_15_00_28"
#DIRAC_PATHCOMPONENTS = 4





do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/tools/xdctools_3_15_00_28
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/tools/
}
