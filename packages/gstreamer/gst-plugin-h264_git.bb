require gst-plugins-git.inc
DEPENDS += "gstreamer gst-plugins-base"
PR = "r2"

SRC_URI = "git://gitorious.org/robclark-gstreamer/gst-plugin-h264.git;protocol=git"
SRC_URI += "file://common-20091119.tar.gz"
SRCREV = "f95a03159f5c4567b025fd52160301b4543a184f"

FILES_${PN} += "${libdir}/gstreamer-0.10/libgst*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgst*.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
