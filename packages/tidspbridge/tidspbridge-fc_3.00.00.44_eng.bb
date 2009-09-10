SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments codegen tools"
LICENSE = "Texas Instruments"
PR = "r0"

inherit dfetch

PV_underscr = `echo "${PV}" | sed -e 's/\./_/g'`

DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/framework_components_${PV_underscr}_eng"
DIRAC_PATHCOMPONENT = "framework_components_${PV_underscr}_eng"
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
	oenote "PV_underscr = ${PV_underscr}"
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/tools/framework_components_${PV_underscr}_eng
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/tools/
}
