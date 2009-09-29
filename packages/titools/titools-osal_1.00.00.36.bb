SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments OSAL"
LICENSE = "Texas Instruments"
PR = "r0"

inherit sdotools-tar

SDOVERS = 1_00_00_36

SDOFILE = osal_${SDOVERS}.tar.gz

SDOPATH = "OSAL/${SDOVERS}/exports/${SDOFILE}"

