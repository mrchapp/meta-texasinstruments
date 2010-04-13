SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Codec Engine"
LICENSE = "Texas Instruments"
PR = "r1"

inherit sdotools-tar 

SDOVERS = 3_00_00_31

SDOFILE = codec_engine_${SDOVERS}.tar.gz

SDOPATH = "Codec_Engine/${SDOVERS}/exports/${SDOFILE}"
