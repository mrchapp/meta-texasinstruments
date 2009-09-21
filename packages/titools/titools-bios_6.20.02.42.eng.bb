SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP BIOS"
LICENSE = "Texas Instruments"
PR = "r0"

inherit sdotools

SDOVERS = 6_20_02_42

SDOFILE = bios_setuplinux_${SDOVERS}_eng.bin

SDOPATH = "BIOS/${SDOVERS}/exports/${SDOFILE}"

