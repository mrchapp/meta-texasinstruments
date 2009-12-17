DESCRIPTION = "Texas Instruments OpenMAX IL Audio Decoder."
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

PR = "r8"

require tiopenmax-audio-git.inc

DEPENDS = "tiopenmax-base tiopenmax-core tiopenmax-osal tiopenmax-lcml alsa-utils"

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

EXTRA_OECONF = "--enable-tests"

S = "${WORKDIR}/git/audio/audio_decode/"

inherit pkgconfig autotools

SRC_URI +="file://amixer.sh"

do_stage() {
    autotools_stage_all
}

do_install_append() {
    install -m 0777 ${WORKDIR}/amixer.sh       ${D}${bindir}
}

