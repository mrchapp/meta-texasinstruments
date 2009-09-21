SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments xdc tools"
LICENSE = "Texas Instruments"
PR = "r0"

inherit sdotools

SDOVERS = 3_15_03_64

SDOFILE = xdctools_setuplinux_${SDOVERS}_eng.bin

SDOPATH = "XDCtools/${SDOVERS}/exports/${SDOFILE}"

