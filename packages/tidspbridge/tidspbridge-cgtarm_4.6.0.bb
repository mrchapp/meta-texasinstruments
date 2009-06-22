SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments codegen tools"
LICENSE = "Texas Instruments"
PR = "r0"

inherit dfetch



#DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/cgtarm-${PV}"
#DIRAC_PATHCOMPONENT = "cgtarm-${PV}"
#DIRAC_PATHCOMPONENTS = 4

DIRAC_PATHFETCH = "/data/EVM_filesystems/x0104108/24.0.8/cgtarm-${PV}"
DIRAC_PATHCOMPONENT = "cgtarm-${PV}"
DIRAC_PATHCOMPONENTS = 4


do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/tools/cgtarm-${PV}
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/tools/
}
