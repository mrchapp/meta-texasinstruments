SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments xdc tools"
LICENSE = "Texas Instruments"
PR = "r2"

inherit sdotools

SDOVERS = 3_15_03_67

SDOFILE = xdctools_setuplinux_${SDOVERS}.bin

SDOPATH = "XDCtools/${SDOVERS}/exports/${SDOFILE}"

