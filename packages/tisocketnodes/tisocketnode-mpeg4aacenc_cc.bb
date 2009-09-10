DESCRIPTION = "Texas Instruments MPEG4 AAC Encoder Socket Node."
PR = "r0"
DEPENDS += "baseimage tisocketnode-usn tisocketnode-mpeg4aacenc-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/... ${SRCREV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/audio/node/mpeg4aac/enc"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

inherit ccasefetch

PV = "4.0+cc+${SRCREV}"


# TODO: on L23 we were using a generic class for socket node
# since build system has changed this class must be updated
# since there is only 1 socket node for now we don't use the
# generic class...


# note: here we rely on the staged baseimage
ENV_VAR = " \
   DEPOT=${STAGING_BINDIR}/dspbridge/tools \
   DSPMAKEROOT=${STAGING_BINDIR}/dspbridge/make \
   DBS_BRIDGE_DIR_C64=${STAGING_BINDIR}/dspbridge/dsp \
   "

# set to release or debug
RELEASE = "release"

SRC_URI = "file://mpeg4aac-enc-build-linux.patch;patch=1"

do_compile() {

    # FIXME: this is a hack because MP4AAC SN has hardcoded link!
    mkdir -p ${S}/audio/alg/aacenc/omap4_he/lib
    cp -a ${STAGING_BINDIR}/dspbridge/audio/alg/aacenc/omap4_he/lib/* ${S}/audio/alg/aacenc/omap4_he/lib

	cd ${S}/audio/node/mpeg4aac/enc
	${ENV_VAR} oe_runmake omap4430${RELEASE}
}

do_install() {
	install -d ${D}${base_libdir}/dsp
	install -m 0644 ${S}/audio/node/mpeg4aac/enc/src/*.dll64T ${D}${base_libdir}/dsp
}

FILES_${PN} = "${base_libdir}/dsp"
