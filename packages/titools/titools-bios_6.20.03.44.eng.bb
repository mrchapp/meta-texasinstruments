SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP BIOS"
LICENSE = "Texas Instruments"
PR = "r4"

inherit sdotools

SDOVERS = 6_20_03_44_eng

SDOFILE = bios_setuplinux_${SDOVERS}.bin

SDOPATH = "BIOS/${SDOVERS}/exports/${SDOFILE}"

do_stage_append() {
     cp ${FILESDIR}/Cache.xs ${SDO_INSTALL_DIR}/bios_${SDOVERS}/packages/ti/sysbios/hal/Cache.xs
}

