DEPENDS = "tidspbridge-lib tiopenmax-common"
DESCRIPTION = "Texas Instruments OpenMAX IL Operating System Abstraction Layer (OSAL)."
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
PR = "r0"

require tiopenmax-audio-git.inc
S = "${WORKDIR}/git/system/mm_osal/"

inherit pkgconfig autotools

FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
	autotools_stage_all
}
