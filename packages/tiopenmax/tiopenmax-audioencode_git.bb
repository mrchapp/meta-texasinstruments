DESCRIPTION = "Texas Instruments OpenMAX IL Audio Encoder."
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

PR = "r3"

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

S = "${WORKDIR}/git/audio/audio_encode/"

inherit pkgconfig autotools

do_stage() {
    autotools_stage_all
}
