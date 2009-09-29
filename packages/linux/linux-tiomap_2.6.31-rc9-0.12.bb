require linux-omap.inc

PR = "r8"

COMPATIBLE_MACHINE = "omap-4430sdp"
DEFAULT_PREFERENCE = "1"

FILESDIR = "${FILE_DIRNAME}/${PN}-git/${MACHINE}"
 
RPROVIDES += "virtual/dspbridge-driver"
PROVIDES += "virtual/dspbridge-driver"
 
# L24.0.12 tag
SRCREV = "ti-2.6.31-rc9-omap4-24.0.12"

SRC_URI = "git://dev.omapzoom.org/pub/scm/integration/kernel-omap4.git;branch=L24.0.12;protocol=git"

S = "${WORKDIR}/git"

# You can supply your own defconfig if you like.  See
# http://bec-systems.com/oe/html/recipes_sources.html for a full explanation
#SRC_URI += "file://defconfig-omap-4430sdp"


do_stage_append() {
	install -d ${STAGING_KERNEL_DIR}/arch/arm/plat-omap/include/mach
	install -m 0644 ${S}/arch/arm/plat-omap/include/mach/*.h ${STAGING_KERNEL_DIR}/arch/arm/plat-omap/include/mach

}

