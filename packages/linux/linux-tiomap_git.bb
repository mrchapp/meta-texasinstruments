DEPENDS_zoom3 = "\
${@base_contains("DISTRO_FEATURES", "hdmi", "hdmi", "", d)} \
"
RDEPENDS="l23-init-extra"

require linux-omap.inc

PR = "r1"

SRC_URI = "git://dev.omapzoom.org/pub/scm/integration/kernel-omap3.git;protocol=git;branch=L23.I3.4"

PV = "2.6+git${SRCREV}"

S = "${WORKDIR}/git"


COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp|omap-3630sdp|zoom2|zoom3"

DEFAULT_PREFERENCE = "1"

# You can supply your own defconfig if you like.  See
# http://bec-systems.com/oe/html/recipes_sources.html for a full explanation
SRC_URI += " \
           file://Revert-DSPBRIDGE-cache-against-kernel-address-fix.patch;patch=1 \
           file://Revert-DSPBRIDGE-cache-operation-against-kernel-ad.patch;patch=1 \
  	   "



