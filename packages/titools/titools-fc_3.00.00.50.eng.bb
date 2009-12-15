SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Framework Components"
LICENSE = "Texas Instruments"
PR = "r1"

inherit sdotools-tar

SDOVERS = 3_00_00_50

SDOFILE = framework_components_${SDOVERS}_eng.tar.gz

SDOPATH = "Framework_Components/${SDOVERS}/exports/${SDOFILE}"

#inherit dfetch

#DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/framework_components_3_00_00_48_eng"
#DIRAC_PATHCOMPONENT = "framework_components_3_00_00_48_eng"
#DIRAC_PATHCOMPONENTS = 4 

#do_stage() {
#    chmod -R +rw ${S}/*
#    install -d ${STAGING_BINDIR}/titools/framework_components_3_00_00_48_eng
#    cp -a ${S}/* ${STAGING_BINDIR}/titools/
#}

