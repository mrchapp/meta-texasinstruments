DESCRIPTION = "Texas Instruments ALSA configuration files"
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

PR = "r1"

inherit pkgconfig

SRC_URI ="file://amixer.sh \
          file://asound.conf \
"

FILES_${PN} += "${sysconfdir}/*"

do_install_append() {
    install -d ${D}${bindir}
    install -m 0777 ${WORKDIR}/amixer.sh       ${D}${bindir}
    install -d ${D}${sysconfdir}
    install -m 0777 ${WORKDIR}/asound.conf     ${D}${sysconfdir}
}

