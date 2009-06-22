SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments xdc tools"
LICENSE = "Texas Instruments"
PR = "r0"

inherit dfetch


#DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/xdctools_3_15_00_39"
#DIRAC_PATHCOMPONENT = "xdctools_3_15_00_39"
#DIRAC_PATHCOMPONENTS = 4

DIRAC_PATHFETCH = "/data/EVM_filesystems/x0104108/24.0.8/xdctools_3_15_00_39"
DIRAC_PATHCOMPONENT = "xdctools_3_15_00_39"
DIRAC_PATHCOMPONENTS = 4


do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/tools/xdctools_3_15_00_39
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/tools/
}
