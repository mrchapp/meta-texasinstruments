SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP BIOS"
LICENSE = "Texas Instruments"
PR = "r3"

inherit sdotools

SDOVERS = 6_20_03_44_eng

SDOFILE = bios_setuplinux_${SDOVERS}.bin

SDOPATH = "BIOS/${SDOVERS}/exports/${SDOFILE}"
