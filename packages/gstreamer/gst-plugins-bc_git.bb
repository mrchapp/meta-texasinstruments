require gst-plugins-git.inc
DEPENDS = "gst-plugins-base"
PR = "r0"

SRC_URI = "git://gitorious.org/gst-plugin-bc/gst-plugin-bc.git;protocol=git"
SRCREV = "39a672916e11dbccb07c3d9ad5b57422fd29b8f0"
SRC_URI += "file://common-20091119.tar.gz"
SRC_URI += "file://nomodule.patch;patch=1"
S = "${WORKDIR}/git"

inherit autotools
    
FILES_${PN} += "${libdir}/gstreamer-0.10/libgst*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgst*.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
