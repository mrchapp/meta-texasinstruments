DESCRIPTION = "OMAP4 Multimedia Applications"
DEPENDS = "gtk+ gst-plugins-base"

RDEPENDS = "gst-plugin-playbin"

RRECOMMENDS = "gst-plugin-mad \
            gst-plugin-id3demux \
            gst-plugin-ivorbis \
            gst-plugin-alsa \
            gst-plugin-ogg"

PR = "r1"

SRC_URI = "git://github.com/tigrux/omap-media-apps.git;protocol=http;tag=${PV}"
S = "${WORKDIR}/git"

FILES_${PN} += "${datadir}/omap4-apps"

inherit autotools
