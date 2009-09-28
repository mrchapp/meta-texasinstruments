DEPENDS = "tiopenmax-base tiopenmax-core tiopenmax-rmproxy tiopenmax-lcml"
DESCRIPTION = "Texas Instruments OpenMAX IL Audio Encoder."
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
PR = "r0"

require tiopenmax-audio-${PV}.inc
S = "${WORKDIR}/git/audio/audio_encode/"
#SRC_URI += "file://aacencuseomap3sn.patch;patch=1"

EXTRA_OECONF = "--enable-tests"

inherit pkgconfig autotools

FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
	autotools_stage_all
}
