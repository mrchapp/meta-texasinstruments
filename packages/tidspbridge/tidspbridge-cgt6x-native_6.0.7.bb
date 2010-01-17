SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments C6x Code generation tools"
LICENSE = "Texas Instruments"
PR = "r0"

inherit sdotools-cgt
SDOFILE = "ti_cgt_c6000_6.0.7_setup_linux_x86.bin"

SDOPATH = "c60/rel6_0_7/build/install/${SDOFILE}"

# FIXME: this is cgt6x not 7x!
SDONAME = "cgt6x"
