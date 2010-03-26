DESCRIPTION = "GStreamer Enhanced Launcher"
RDEPENDS = "gst-plugins-base"
PRIORITY = "optional"
PR = "r1"

SRC_URI = "git://github.com/tigrux/gst-auto-launch.git;protocol=git"
SRCREV = "b49945d04354dd6ee6bf66cb924f4267cecc0d58"
S = "${WORKDIR}/git"

inherit autotools

