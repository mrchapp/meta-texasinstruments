SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments codegen tools"
LICENSE = "Texas Instruments"
PR = "r0"

inherit dfetch


#DIRAC_PATHFETCH = "/data/EVM_filesystems/x0104108/sque/cgt7x-${PV}"
#DIRAC_PATHCOMPONENT = "cgt7x-${PV}"
#DIRAC_PATHCOMPONENTS = 4

DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/cgt7x-${PV}"
DIRAC_PATHCOMPONENT = "cgt7x-${PV}"
DIRAC_PATHCOMPONENTS = 4


#CCASE_SPEC = "%\
#	element /vobs/WTSD_TOOLS/compiler/CGTools/linux/... CGT7X-7.0.0 \
#	element * /main/LATEST %\
#	 "

#CCASE_PATHFETCH = "/vobs/WTSD_TOOLS/compiler/CGTools/linux"
#CCASE_PATHCOMPONENT = "linux"
#CCASE_PATHCOMPONENTS = "4"

#inherit ccasefetch


do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/tools/cgt7x-${PV}
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/tools/
}
