PRIORITY = "optional"
DESCRIPTION = "Texas Instruments USN Socket Node."
LICENSE = "LGPL"
PR = "r1"
DEPENDS  = "baseimage tidspbridge-samples"

inherit ccasefetch

PV = "4.0+cc+${SRCREV}"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/usn/... ${SRCREV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/usn"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

# note: here we rely on the staged baseimage
ENV_VAR = " \
   DEPOT=${STAGING_BINDIR}/dspbridge/tools \
   DSPMAKEROOT=${STAGING_BINDIR}/dspbridge/make \
   DBS_BRIDGE_DIR_C64=${STAGING_BINDIR}/dspbridge/dsp \
   "

SRC_URI = "file://usn-build-linux.patch;patch=1"

# set to release or debug
RELEASE = "release"


do_compile() {
	cd ${S}/system/usn
	${ENV_VAR} oe_runmake omap4430${RELEASE}
}

# staging USN to compile socket nodes
# TODO: optimize... don't need to stage everything
do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/system/usn
	cp -a ${S}/system/usn/* ${STAGING_BINDIR}/dspbridge/system/usn
}

do_install() {
	install -d ${D}${base_libdir}/dsp
	install -m 0644 ${S}/system/usn/src/usn.dll64T ${D}${base_libdir}/dsp
}

FILES_${PN} = "${base_libdir}/dsp"
