DESCRIPTION = "Texas Instruments OpenMAX IL Core."
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
PR = "r3"

require tiopenmax-audio-git.inc

S = "${WORKDIR}/git/system/omx_core/"

inherit pkgconfig autotools

FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
	autotools_stage_all
}
