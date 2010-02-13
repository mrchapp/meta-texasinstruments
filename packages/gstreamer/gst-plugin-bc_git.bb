require gst-plugins-git.inc
DEPENDS = "gst-plugins-base"
PR = "r2"

SRC_URI = "git://gitorious.org/gst-plugin-bc/gst-plugin-bc.git;protocol=git"
SRCREV = "e14e249ef6cb67e91be9198b71efc61eb84c11b5"
SRC_URI += "file://common-20091119.tar.gz"
S = "${WORKDIR}/git"

inherit autotools
    
FILES_${PN} += "${libdir}/gstreamer-0.10/libgst*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgst*.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
