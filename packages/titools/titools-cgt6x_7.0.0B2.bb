SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments C6x Code generation tools"
LICENSE = "Texas Instruments"
PR = "r0"

inherit sdotools-cgt

SDOFILE = "ti_cgt_c6000_7.0.0B2_setup_linux_x86.bin"

SDOPATH = "c60/rel7_0_0_beta2/build/install/${SDOFILE}"

# FIXME: this is cgt6x not 7x!
SDONAME = "cgt7x"

