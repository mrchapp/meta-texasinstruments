DESCRIPTION = "GStreamer Enhanced Launcher"
DEPENDS = "gst-plugins-base"
PRIORITY = "optional"
PR = "r2"

SRC_URI = "git://github.com/tigrux/gst-auto-launch.git;protocol=git;tag=${PV}"
S = "${WORKDIR}/git"

inherit autotools

