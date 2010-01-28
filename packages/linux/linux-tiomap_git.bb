require linux-omap.inc

PR = "r6"

SRC_URI = "git://dev.omapzoom.org/pub/scm/integration/kernel-omap4.git;protocol=git \
          file://defconfig-omap-4430sdp \
          ${@base_contains("DISTRO_FEATURES", "hdmi", "https://gforge01.dal.design.ti.com/gf/download/docmanfileversion/504/2977/OMAP4_HDMI_AV_coreLib_TI_restricted_L24.2_v2.patch;patch=1", "", d)} \
          ${@base_contains("DISTRO_FEATURES", "hdmi", "https://gforge01.dal.design.ti.com/gf/download/docmanfileversion/557/3141/hdmi-24.3.patch;patch=1", "", d)} \
"

PV = "2.6+git+${SRCREV}"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "omap-4430sdp"

DEFAULT_PREFERENCE = "1"

FILESDIR = "${FILE_DIRNAME}/${PN}-git/${MACHINE}"

RPROVIDES += "virtual/dspbridge-driver"
PROVIDES += "virtual/dspbridge-driver"

