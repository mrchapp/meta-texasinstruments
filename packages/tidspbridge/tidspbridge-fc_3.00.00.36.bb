SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments codegen tools"
LICENSE = "Texas Instruments"
PR = "r0"

inherit dfetch


DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/framework_components_3_00_00_36"
DIRAC_PATHCOMPONENT = "framework_components_3_00_00_36"
DIRAC_PATHCOMPONENTS = 4

#CCASE_SPEC = "%\
#	  element /vobs/WTSD_TOOLS/dsp/fc/... FC-3.0.0.27 %\
#	  element * /main/LATEST %\
# 	  "

#CCASE_PATHFETCH = "/vobs/WTSD_TOOLS/dsp/fc"
#CCASE_PATHCOMPONENT = "fc"
#CCASE_PATHCOMPONENTS = "3"

#inherit ccasefetch


do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/tools/framework_components_3_00_00_36
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/tools/
}
