SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments OSAL"
LICENSE = "Texas Instruments"
PR = "r1"

inherit sdotools-tar

SDOVERS = 7_00_00_27

SDOFILE = xdais_${SDOVERS}.tar.gz

SDOPATH = "XDAIS/${SDOVERS}/exports/${SDOFILE}"

