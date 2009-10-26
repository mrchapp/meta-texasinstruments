SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Framework Components"
LICENSE = "Texas Instruments"
PR = "r1"

inherit sdotools-tar

SDOVERS = 3_00_00_48

SDOFILE = framework_components_${SDOVERS}_eng.tar.gz

SDOPATH = "Framework_Components/${SDOVERS}/exports/${SDOFILE}"
