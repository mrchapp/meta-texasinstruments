require linux-omap.inc

PR = "r8"

SRC_URI = "git://dev.omapzoom.org/pub/scm/integration/kernel-omap4.git;protocol=git \
          file://defconfig-omap-4430sdp \
"

PV = "2.6+git+${SRCREV}"

S = "${WORKDIR}/git"

DEFAULT_PREFERENCE = "1"


