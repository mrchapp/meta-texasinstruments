require gst-plugins-git.inc
DEPENDS += "gstreamer gst-plugins-base"
PR = "r1"

SRC_URI = "git://sealion.sc.ti.com/git/gst-plugin-h264;protocol=git"
SRC_URI += "file://common-20091119.tar.gz"
SRCREV = "40907c80678c1d720064d1a4c9126d99e1c49227"

FILES_${PN} += "${libdir}/gstreamer-0.10/libgst*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgst*.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
