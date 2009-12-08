DEPENDS = "tiopenmax-base tiopenmax-core tiopenmax-osal tiopenmax-lcml alsa-utils"
DESCRIPTION = "Texas Instruments OpenMAX IL Audio Decoder."
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
RDEPENDS = " alsa-utils-alsamixer \
             alsa-utils-midi \
             alsa-utils-aplay \
             alsa-utils-amixer \
             alsa-utils-aconnect \
             alsa-utils-iecset \
             alsa-utils-speakertest \
             alsa-utils-aseqnet \
             alsa-utils-aseqdump \
             alsa-utils-alsaconf \
             alsa-utils-alsactl "

PR = "r7"

require tiopenmax-audio-git.inc

SRC_URI +="file://amixer.sh"

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
	install -m 0777 ${WORKDIR}/amixer.sh       ${D}${bindir}
}

