SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments ARM Code generation tools"
LICENSE = "Texas Instruments"
PR = "r1"

#inherit sdotools-cgt
#
#SDOFILE = "ti_cgt_tms470_4.6.0_setup_linux_x86.bin"
#
#SDOPATH = "arm/rel4_6_0/build/install/${SDOFILE}"
#
#SDONAME = "cgtarm"

inherit dfetch

DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/cgtarm-4.6.0"
DIRAC_PATHCOMPONENT = "cgtarm-4.6.0"
DIRAC_PATHCOMPONENTS = 4 

do_stage() {
	chmod -R +rw ${S}/*
	install -d ${STAGING_BINDIR}/titools/cgtarm-4.6.0
	cp -a ${S}/* ${STAGING_BINDIR}/titools/	
}
