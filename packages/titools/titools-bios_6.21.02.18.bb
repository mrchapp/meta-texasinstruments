SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP BIOS"
LICENSE = "Texas Instruments"
PR = "r4"

inherit sdotools

SDOVERS = 6_21_02_18

SDOFILE = bios_setuplinux_${SDOVERS}_eng.bin

SDOPATH = "BIOS/${SDOVERS}/exports/${SDOFILE}"

