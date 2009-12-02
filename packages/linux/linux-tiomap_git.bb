require linux-omap.inc

PR = "r1"

SRC_URI = "git://dev.omapzoom.org/pub/scm/integration/kernel-omap4.git;branch=L24.1;protocol=git \
          file://defconfig-omap-4430sdp \
"

PV = "2.6+git+${SRCREV}"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "omap-4430sdp"

DEFAULT_PREFERENCE = "1"

FILESDIR = "${FILE_DIRNAME}/${PN}-git/${MACHINE}"

RPROVIDES += "virtual/dspbridge-driver"
PROVIDES += "virtual/dspbridge-driver"

