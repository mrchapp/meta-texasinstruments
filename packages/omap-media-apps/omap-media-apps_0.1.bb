DESCRIPTION = "OMAP4 Multimedia Applications"
DEPENDS = "gtk+ gst-plugins-base"

RDEPENDS = "gst-plugin-playbin"

RRECOMMENDS = "gst-plugin-mad \
            gst-plugin-id3demux \
            gst-plugin-ivorbis \
            gst-plugin-alsa \
            gst-plugin-ogg"

PV = "0.1"
PR = "r0"

SRCREV = "708c5650a1387fdcd5323407fc6c5d2f45f7905d"
SRC_URI = "git://github.com/tigrux/omap-media-apps.git;protocol=http"
S = "${WORKDIR}/git"

inherit autotools
