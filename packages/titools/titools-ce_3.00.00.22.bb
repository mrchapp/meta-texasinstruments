SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Codec Engine"
LICENSE = "Texas Instruments"
PR = "r0"

inherit sdotools-tar

SDOVERS = 3_00_00_22

SDOFILE = codec_engine_${SDOVERS}.tar.gz

SDOPATH = "Codec_Engine/${SDOVERS}/exports/${SDOFILE}"
