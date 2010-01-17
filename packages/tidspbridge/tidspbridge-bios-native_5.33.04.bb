SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP BIOS"
LICENSE = "Texas Instruments"
PR = "r0"

inherit sdotools

SDOVERS = 5_33_04

SDOFILE = bios_setuplinux_${SDOVERS}.bin

SDOPATH = "BIOS/${SDOVERS}/exports/${SDOFILE}"
