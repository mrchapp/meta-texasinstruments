DESCRIPTION = "GStreamer Trace library"
DEPENDS = "gst-plugins-base"
PRIORITY = "optional"
LICENSE = "GPL"
PR = "r0"

SRC_URI = "git://anongit.freedesktop.org/~ensonic/gst-tracelib;protocol=git"
SRCREV = "8b72c1af6c069369293c263697185b2858f7406b"
S = "${WORKDIR}/git"

inherit autotools
