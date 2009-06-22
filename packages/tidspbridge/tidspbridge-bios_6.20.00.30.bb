SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP Toolchain"
LICENSE = "Texas Instruments"
PR = "r0"

inherit dfetch

#DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/bios_6_20_00_30"
#DIRAC_PATHCOMPONENT = "bios_6_20_00_30"
#DIRAC_PATHCOMPONENTS = 4 

DIRAC_PATHFETCH = "/data/EVM_filesystems/x0104108/24.0.8/bios_6_20_00_30"
DIRAC_PATHCOMPONENT = "bios_6_20_00_30"
DIRAC_PATHCOMPONENTS = 4

#CCASE_SPEC = "%\
#	element /vobs/WTSD_TOOLS/dsp/bios6/linux... BIOS_${PV}%\
#	element * /main/LATEST%"

#CCASE_PATHFETCH = "/vobs/WTSD_TOOLS/dsp/bios6/linux"
#CCASE_PATHCOMPONENT = "linux"
#CCASE_PATHCOMPONENTS = "4"

#inherit ccasefetch


do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/tools/bios_6_20_00_30
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/tools/
	
}
