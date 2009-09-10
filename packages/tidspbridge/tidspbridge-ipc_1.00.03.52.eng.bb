SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments IPC library"
LICENSE = "Texas Instruments"
PR = "r0"

inherit sdotools-tar

SDOVERS = 1_00_03_52

SDOFILE = ipc_${SDOVERS}_eng.tar.gz

SDOPATH = "IPC/${SDOVERS}/exports/${SDOFILE}"


