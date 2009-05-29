require linux-omap.inc

PR = "r0"

COMPATIBLE_MACHINE = "omap-4430sdp|omap-4430sdphigh"
DEFAULT_PREFERENCE = "1"

FILESDIR = "${FILE_DIRNAME}/${PN}-git/${MACHINE}"
 
RPROVIDES += "virtual/dspbridge-driver"
PROVIDES += "virtual/dspbridge-driver"
 
# L24.0.6 tag
SRCREV = "ti-2.6.27-omap4-24.0.6"
 

SRC_URI = "git://git.omapzoom.org/repo/omapkernel.git;branch=master;protocol=http"

#S = "${WORKDIR}/linux-omap4"
S = "${WORKDIR}/git"

# You can supply your own defconfig if you like.  See
# http://bec-systems.com/oe/html/recipes_sources.html for a full explanation
#SRC_URI_omap-3430ldp += "file://defconfig-omap-3430ldp"
#SRC_URI_omap-3430sdp += "file://defconfig-omap-3430sdp"


do_stage_append() {
	install -d ${STAGING_KERNEL_DIR}/arch/arm/plat-omap/include/mach
	install -m 0644 ${S}/arch/arm/plat-omap/include/mach/*.h ${STAGING_KERNEL_DIR}/arch/arm/plat-omap/include/mach

}
