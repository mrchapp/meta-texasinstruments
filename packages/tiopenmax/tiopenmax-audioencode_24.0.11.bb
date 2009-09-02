DEPENDS = "tiopenmax-base tiopenmax-core tiopenmax-osal tiopenmax-lcml"
DESCRIPTION = "Texas Instruments OpenMAX IL Audio Decoder."
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
PR = "r1"

require tiopenmax-audio-git.inc
S = "${WORKDIR}/git/audio/audio_encode/"

#SRC_URI += "file://rmproxy-depend.patch;patch=1"

EXTRA_OECONF = "--enable-tests"

inherit pkgconfig autotools

FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
	autotools_stage_all
}
