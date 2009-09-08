require linux-omap.inc

PR = "r0"

COMPATIBLE_MACHINE = "omap-4430sdp"
DEFAULT_PREFERENCE = "1"

FILESDIR = "${FILE_DIRNAME}/${PN}-git/${MACHINE}"
 
RPROVIDES += "virtual/dspbridge-driver"
PROVIDES += "virtual/dspbridge-driver"
 
# L24.0.9 tag
SRCREV = "ti-2.6.30-rc6-omap4-24.0.9"
#SRCREV = "ti-teslabridgedev-omap4-24.0.9"
 

SRC_URI = "git://dev.omapzoom.org/pub/scm/integration/kernel-omap4.git;branch=master;protocol=git"
#SRC_URI = "git://dev.omapzoom.org/pub/scm/tisyslink/kernel-syslink.git;branch=teslabridge-dev;protocol=git"

S = "${WORKDIR}/git"

# You can supply your own defconfig if you like.  See
# http://bec-systems.com/oe/html/recipes_sources.html for a full explanation
#SRC_URI_omap-3430ldp += "file://defconfig-omap-3430ldp"
#SRC_URI_omap-3430sdp += "file://defconfig-omap-3430sdp"


do_stage_append() {
	install -d ${STAGING_KERNEL_DIR}/arch/arm/plat-omap/include/mach
	install -m 0644 ${S}/arch/arm/plat-omap/include/mach/*.h ${STAGING_KERNEL_DIR}/arch/arm/plat-omap/include/mach

}
