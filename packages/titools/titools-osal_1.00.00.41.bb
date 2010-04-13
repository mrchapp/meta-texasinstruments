SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments OSAL"
LICENSE = "Texas Instruments"
PR = "r1"

inherit sdotools-tar

SDOVERS = 1_00_00_41

SDOFILE = osal_${SDOVERS}.tar.gz

SDOPATH = "OSAL/${SDOVERS}/exports/${SDOFILE}"




