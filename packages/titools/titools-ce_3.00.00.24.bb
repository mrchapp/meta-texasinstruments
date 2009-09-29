SECTION = "toolchains"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Codec Engine"
LICENSE = "Texas Instruments"
PR = "r0"

inherit sdotools-tar

SDOVERS = 3_00_00_24

SDOFILE = codec_engine_${SDOVERS}.tar.gz

SDOPATH = "Codec_Engine/${SDOVERS}/exports/${SDOFILE}"

#inherit dfetch
#
#DIRAC_FILE=codec_engine_3_00_00_22
#
#DIRAC_PATHFETCH = "/data/omapts/linux/dsp-tc/${DIRAC_FILE}"
#DIRAC_PATHCOMPONENT = "${DIRAC_FILE}"
#DIRAC_PATHCOMPONENTS = 4
#
#
#do_stage() {
#	chmod -R +w ${S}/*
#	install -d ${STAGING_BINDIR}/titools/${DIRAC_FILE}
#	cp -a ${S}/* ${STAGING_BINDIR}/titools/${DIRAC_FILE}
#}
