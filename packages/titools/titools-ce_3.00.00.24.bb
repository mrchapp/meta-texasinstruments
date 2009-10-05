SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Codec Engine"
LICENSE = "Texas Instruments"
PR = "r1"

#inherit sdotools-tar
#
#SDOVERS = 3_00_00_24
#
#SDOFILE = codec_engine_${SDOVERS}.tar.gz
#
#SDOPATH = "Codec_Engine/${SDOVERS}/exports/${SDOFILE}"

inherit dfetch

DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/codec_engine_3_00_00_24"
DIRAC_PATHCOMPONENT = "codec_engine_3_00_00_24"
DIRAC_PATHCOMPONENTS = 4 

do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/titools/codec_engine_3_00_00_24
	cp -a ${S}/* ${STAGING_BINDIR}/titools/	
}
