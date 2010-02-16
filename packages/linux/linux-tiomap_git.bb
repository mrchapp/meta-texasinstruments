require linux-omap.inc

PR = "r7"

SRC_URI = "git://dev.omapzoom.org/pub/scm/integration/kernel-omap4.git;protocol=git \
          file://defconfig-omap-4430sdp \
          ${@base_contains("DISTRO_FEATURES", "hdmi", "https://gforge01.dal.design.ti.com/gf/download/docmanfileversion/567/3229/OMAP4_HDMI_AV_coreLib_TI_restricted_L24.4_v2.patch;patch=1", "", d)} \
"

PV = "2.6+git+${SRCREV}"

S = "${WORKDIR}/git"

DEFAULT_PREFERENCE = "1"


