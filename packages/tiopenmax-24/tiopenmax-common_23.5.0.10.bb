DEPENDS = "tidspbridge-lib"
DESCRIPTION = "Texas Instruments Common files for OpenMAX IL."
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
PR = "r0"

require tiopenmax-system-git.inc
S = "${WORKDIR}/git/system/common"

inherit pkgconfig autotools

FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
	autotools_stage_all
}
