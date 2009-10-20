DESCRIPTION = "Texas Instruments JPEG Encoder Socket Node."
PR = "r1"
DEPENDS += "tisocketnode-jpegenc-codec \
	tisocketnode-conversions"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/image/node/jpeg/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

SN_DIR=${S}/image/node/jpeg/enc

inherit ccasefetch tisocketnode

# FIXME: This headers need to go into proper locations.
do_compile_prepend() {
	## Getting conversions files
	mkdir -p ${S}/system/utils/inc
	cp -a ${STAGING_BINDIR}/dspbridge/video/lib/conversions/inc/* ${S}/system/utils/inc
}
