DESCRIPTION = "GStreamer Enhanced Launcher"
RDEPENDS = "gst-plugins-base"
PRIORITY = "optional"
PR = "r0"

SRC_URI = "git://github.com/tigrux/gst-auto-launch.git;protocol=git"
SRCREV = "a495c7e004ea81b3a06d2966a7239d4ad58fd89b"
S = "${WORKDIR}/git"

inherit autotools

