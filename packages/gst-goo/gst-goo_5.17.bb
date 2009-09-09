SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "glib-2.0 libgoo gst-plugins-base"
DESCRIPTION = "GStreamer plug-ins for OpenMAX IL based on LibGoo"
LICENSE = "LGPL"
PR = "r0"

SRCREV = "c423239a818e1bb89bd4ab7754e1b33a7546023f"
SRC_URI = "git://git.omapzoom.org/repo/gst-goo.git;protocol=http"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstgoo.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstgoo.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"

do_stage() {
	autotools_stage_all
}

PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"
