DESCRIPTION = "Music player"
LICENSE = "GPL"
DEPENDS = "gtk+ gstreamer"

RDEPENDS = "gst-plugin-audioconvert \
        gst-plugin-audioresample \
        gst-plugin-typefindfunctions \
        gst-plugin-playbin"

RRECOMMENDS = "gst-plugin-mad \
            gst-plugin-id3demux \
            gst-plugin-ivorbis \
            gst-plugin-alsa \
            gst-plugin-ogg"

PV = "0.0+svnr${SRCREV}"
PR = "r1"

S = "${WORKDIR}/${PN}"

SRC_URI = "svn://svn.o-hand.com/repos/misc/trunk;module=${PN};proto=http"
SRC_URI += "file://playbin2.patch;patch=1"
SRC_URI += "file://debug-support.patch;patch=1"

inherit autotools pkgconfig
