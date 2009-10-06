SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments xdc tools"
LICENSE = "Texas Instruments"
PR = "r1"

#inherit sdotools
#
#SDOVERS = 3_15_03_67
#
#SDOFILE = xdctools_setuplinux_${SDOVERS}.bin
#
#SDOPATH = "XDCtools/${SDOVERS}/exports/${SDOFILE}"

inherit dfetch

DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/xdctools_3_15_03_67"
DIRAC_PATHCOMPONENT = "xdctools_3_15_03_67"
DIRAC_PATHCOMPONENTS = 4 

do_stage() {
	chmod -R +rw ${S}/*
	install -d ${STAGING_BINDIR}/titools/xdctools_3_15_03_67
	cp -a ${S}/* ${STAGING_BINDIR}/titools/	
}

