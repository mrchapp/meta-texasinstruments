SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "glib-2.0 libgoo gst-plugins-base"
DESCRIPTION = "GStreamer plug-ins for OpenMAX IL based on LibGoo"
LICENSE = "LGPL"
PR = "r8"

SRC_URI = "git://git.omapzoom.org/repo/gst-goo.git;protocol=http;tag=v${PV}"
# Temporarily add this work for JPEG Encoder and PPLIB
SRC_URI +="file://0001-jpegenc-Add-support-for-PPLIB.patch;patch=1"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstgoo.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstgoo.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"

do_stage() {
	autotools_stage_all
}
