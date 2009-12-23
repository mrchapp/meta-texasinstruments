SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "WLAN stack (kernel module, libs, wpa_supplicant)"
DEPENDS = "linux-tiomap wilink-firmware"
LICENSE = "GPL"
PR = "r1"

inherit module pkgconfig

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "omap-4430sdp|omap-4430sdphigh"

PACKAGES = "${PN} ${PN}-dbg"

S = "${WORKDIR}/git/"

PV = "0.0+git+${SRCREV}"

SRC_URI = "git://dev.omapzoom.org/pub/scm/pradeep/wlan-1283.git;protocol=git;branch=master \
        file://makefile-ar.patch;patch=1 \
"

FILES_${PN} = "/wlan/*"
FILES_${PN}-dbg = "/wlan/.debug/*"

do_compile() {
	cd ${S}/wilink7/platforms/os/linux
	cp ${STAGING_DIR_TARGET}/fw/firmware.bin ${S}/wilink7/platforms/os/linux/.

	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

#    make ARCH=arm HOST_PLATFORM=${MACHINE} KERNEL_DIR=${STAGING_KERNEL_DIR} \
#	CROSS_COMPILE=${TARGET_PREFIX} BUILD_SUPPL=n clean

	make ARCH=arm HOST_PLATFORM=sdc4430 KERNEL_DIR=${STAGING_KERNEL_DIR} \
	CROSS_COMPILE=${TARGET_PREFIX} BUILD_SUPPL=n all
}

do_install() {
	install -d ${D}/wlan
	install -m 755 ${S}/wilink7/platforms/os/linux/firmware.bin ${D}/wlan
	install -m 0644 ${S}/wilink7/external_drivers/sdc4430/Linux/standard_sdio/sdio.ko ${D}/wlan
	install -m 0644 ${S}/wilink7/platforms/os/linux/tiwlan_drv.ko ${D}/wlan
	install -m 755 ${S}/wilink7/platforms/os/linux/tiwlan.ini ${D}/wlan
	install -m 755 ${S}/wilink7/platforms/os/linux/tiwlan_loader ${D}/wlan
	install -m 755 ${S}/wilink7/platforms/os/linux/wlan_cu ${D}/wlan
}

