require gst-plugins-git.inc
DEPENDS += "gstreamer gst-plugins-base"
PR = "r3"

SRC_URI += "git://gitorious.org/robclark-gstreamer/gst-plugin-h264.git;protocol=git"

FILES_${PN} += "${libdir}/gstreamer-0.10/libgst*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgst*.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
