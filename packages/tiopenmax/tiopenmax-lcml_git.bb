DESCRIPTION = "Texas Instruments OpenMAX IL Linux Common Media Library (LCML)."
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "tiopenmax-core tidspbridge-lib"
inherit pkgconfig autotools

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
PR = "r2"

require tiopenmax-audio-git.inc

S = "${WORKDIR}/git/system/lcml/"

FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
    autotools_stage_all
}

