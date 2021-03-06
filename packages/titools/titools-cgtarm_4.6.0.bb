SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments ARM Code generation tools"
LICENSE = "Texas Instruments"
PR = "r2"

inherit sdotools-cgt

SDOFILE = "ti_cgt_tms470_4.6.0_setup_linux_x86.bin"

SDOPATH = "arm/rel4_6_0/build/install/${SDOFILE}"

SDONAME = "cgtarm"

