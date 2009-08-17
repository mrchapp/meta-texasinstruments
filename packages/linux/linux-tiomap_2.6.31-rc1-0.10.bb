require linux-omap.inc

PR = "r0"

COMPATIBLE_MACHINE = "omap-4430sdp"
DEFAULT_PREFERENCE = "1"

FILESDIR = "${FILE_DIRNAME}/${PN}-git/${MACHINE}"
 
RPROVIDES += "virtual/dspbridge-driver"
PROVIDES += "virtual/dspbridge-driver"
 
# L24.0.10 tag
SRCREV = "ti-2.6.31-rc1-omap4-24.0.10"
#SRCREV = "ti-tesladev-omap4-24.0.10-pre"
#SRCREV = "ti-teslabridgedev-omap4-24.0.10"
 

SRC_URI = "\
		git://dev.omapzoom.org/pub/scm/integration/kernel-omap4.git;branch=L24.0.10;protocol=git \
		file://0001-SYSLINK-ipc-fix-messageq-create-and-delete.patch;patch=1 \
		file://0002-SYSLINK-comment-out-mailbox-clocks-for-OMAP4.patch;patch=1 \
		file://0003-SYSLINK-ipc-sharedregion-fixes-for-sysmgr-validat.patch;patch=1 \
		file://0004-SYSLINK-sys-manager-changes-and-bug-fixes.patch;patch=1 \
		file://0005-SYSLINK-ipc-sys-manager-structure-fixes.patch;patch=1 \
		file://0006-Subject-SYSLINK-fixes-for-shutdown-stage.patch;patch=1 \
		file://0007-SYSINK-ipc-add-sys-manager-config-option.patch;patch=1 \
		file://0008-SYSLINK-ipc-fixes-to-sys-manager.patch;patch=1 \
	"
#SRC_URI = "git://dev.omapzoom.org/pub/scm/tisyslink/kernel-syslink.git;branch=syslink-dev-v2.6.31-rc1;protocol=git"
#SRC_URI = "git://dev.omapzoom.org/pub/scm/integration/kernel-omap4.git;branch=L24.0.10;protocol=git"

S = "${WORKDIR}/git"

# You can supply your own defconfig if you like.  See
# http://bec-systems.com/oe/html/recipes_sources.html for a full explanation
#SRC_URI_omap-3430ldp += "file://defconfig-omap-3430ldp"
#SRC_URI_omap-3430sdp += "file://defconfig-omap-3430sdp"


do_stage_append() {
	install -d ${STAGING_KERNEL_DIR}/arch/arm/plat-omap/include/mach
	install -m 0644 ${S}/arch/arm/plat-omap/include/mach/*.h ${STAGING_KERNEL_DIR}/arch/arm/plat-omap/include/mach

}
