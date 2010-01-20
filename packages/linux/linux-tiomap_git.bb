require linux-omap.inc

PR = "r5"

SRC_URI = "git://dev.omapzoom.org/pub/scm/integration/kernel-omap4.git;protocol=git \
          file://defconfig-omap-4430sdp \
          ${@base_contains("DISTRO_FEATURES", "hdmi", "https://gforge01.dal.design.ti.com/gf/download/docmanfileversion/499/2970/OMAP4_HDMI_AV_coreLib_TI_restricted_L24.1_E_EDID_v4.patch;patch=1", "", d)} \
"

PV = "2.6+git+${SRCREV}"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "omap-4430sdp"

DEFAULT_PREFERENCE = "1"

FILESDIR = "${FILE_DIRNAME}/${PN}-git/${MACHINE}"

RPROVIDES += "virtual/dspbridge-driver"
PROVIDES += "virtual/dspbridge-driver"

