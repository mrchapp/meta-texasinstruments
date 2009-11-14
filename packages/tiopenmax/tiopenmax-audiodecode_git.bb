DEPENDS = "tiopenmax-base tiopenmax-core tiopenmax-osal tiopenmax-lcml alsa-utils"
DESCRIPTION = "Texas Instruments OpenMAX IL Audio Decoder."
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
RDEPENDS = "alsa-utils-aplay"
PR = "r5"

require tiopenmax-audio-git.inc

SRC_URI +="file://more_snddevices"

S = "${WORKDIR}/git/audio/audio_decode/"

EXTRA_OECONF = "--enable-tests"

inherit pkgconfig autotools

FILES_${PN} += "${libdir}/*.so ${bindir}/*"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
	autotools_stage_all
}

do_install_append() {
	install -m 0777 ${WORKDIR}/more_snddevices ${D}${bindir}
}

