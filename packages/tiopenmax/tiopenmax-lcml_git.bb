DESCRIPTION = "Texas Instruments OpenMAX IL Linux Common Media Library (LCML)."
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "tiopenmax-core tidspbridge-lib"
inherit pkgconfig autotools

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
PR = "r2"

require tiopenmax-audio-git.inc

SRC_URI += " file://libdspbridge.pc "
S = "${WORKDIR}/git/system/lcml/"

FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_configure_prepend() {
	cp ${WORKDIR}/libdspbridge.pc ${STAGING_LIBDIR}/pkgconfig
}

do_stage() {
	autotools_stage_all
}

