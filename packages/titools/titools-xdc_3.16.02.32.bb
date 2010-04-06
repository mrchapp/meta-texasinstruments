SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments XDC tools"
LICENSE = "Texas Instruments"
PR = "r2"

inherit sdotools

SDOVERS = 3_16_02_32

SDOFILE = xdctools_setuplinux_${SDOVERS}.bin

SDOPATH = "XDCtools/${SDOVERS}/exports/${SDOFILE}"

