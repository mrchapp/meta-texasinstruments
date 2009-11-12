SECTION = "multimedia"
PRIORITY = "optional"
DEPENDS = "glib-2.0 gst-plugins-base"
DESCRIPTION = "Trace library for GStreamer"
LICENSE = "LGPL"
PR = "r0"
PV = "0.2+git${SRCREV}"
S = "${WORKDIR}/git"

SRC_URI = "git://anongit.freedesktop.org/~ensonic/gst-tracelib;protocol=git"
SRCREV = "4e88290b24a12dacaa4a814f2f0fb66b7cc4e06e"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
