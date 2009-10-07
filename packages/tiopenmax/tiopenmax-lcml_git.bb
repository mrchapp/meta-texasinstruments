DEPENDS = "tidspbridge-lib"
DESCRIPTION = "Texas Instruments OpenMAX IL Linux Common Media Library (LCML)."
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
PR = "r1"

require tiopenmax-audio-git.inc

S = "${WORKDIR}/git/system/lcml/"

inherit pkgconfig autotools

FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
	autotools_stage_all
}