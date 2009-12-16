SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "WLAN stack (kernel module, libs, wpa_supplicant)"
DEPENDS = "linux-tiomap wilink-firmware"
LICENSE = "GPL"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "omap-4430sdp|omap-4430sdphigh"

PACKAGES = "${PN}"
FILES_${PN} += "/wlan"

inherit pkgconfig

S = "${WORKDIR}/git/wilink7"

PV = "0.0+git+${SRCREV}"

SRC_URI = "git://dev.omapzoom.org/pub/scm/pradeep/wlan-1283.git;protocol=git;branch=master"

do_compile() {
	cd ${S}/platforms/os/linux
	cp ${STAGING_DIR_TARGET}/fw/firmware.bin ${S}/platforms/os/linux/.

#	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

#	oe_runmake ARCH=arm HOST_PLATFORM=${MACHINE} KERNEL_DIR=${STAGING_KERNEL_DIR} \
#	CROSS_COMPILE=${AR%-*}- BUILD_SUPPL=n clean

	oe_runmake ARCH=arm HOST_PLATFORM=${MACHINE} KERNEL_DIR=${STAGING_KERNEL_DIR} \
	CROSS_COMPILE=${AR%-*}- BUILD_SUPPL=n
}

