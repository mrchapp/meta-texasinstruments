require gst-plugins-git.inc
DEPENDS = "gst-plugins-base"
PR = "r1"

SRC_URI = "git://gitorious.org/gst-plugin-bc/gst-plugin-bc.git;protocol=git"
SRCREV = "dca16cd0c118c23c648f516df220e6c57b9c759f"
SRC_URI += "file://common-20091119.tar.gz"
S = "${WORKDIR}/git"

inherit autotools
    
FILES_${PN} += "${libdir}/gstreamer-0.10/libgst*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgst*.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
