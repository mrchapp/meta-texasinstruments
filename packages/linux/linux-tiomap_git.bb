require linux-omap.inc

PR = "r2"

SRC_URI = "git://dev.omapzoom.org/pub/scm/integration/kernel-omap3.git;protocol=git"

PV = "2.6+git${SRCREV}"

S = "${WORKDIR}/git"


COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp|zoom2"

DEFAULT_PREFERENCE = "1"

RPROVIDES += "virtual/dspbridge-driver"
PROVIDES += "virtual/dspbridge-driver"

# You can supply your own defconfig if you like.  See
# http://bec-systems.com/oe/html/recipes_sources.html for a full explanation
#SRC_URI += " \
#           file://<patch-name>.patch;patch=1 \
#  	   "

# work-around for touchscreen problem (remove this when proper soln is found):
#ADD_DISTRO_FEATURES += "sed -i 's/# CONFIG_INTERCONNECT_IO_POSTING is not set/CONFIG_INTERCONNECT_IO_POSTING=y/' ${S}/.config"



