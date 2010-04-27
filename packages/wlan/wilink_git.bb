SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "WLAN stack (kernel module, libs, wpa_supplicant)"
DEPENDS = "linux-tiomap wilink-firmware"
LICENSE = "BSD"
PR = "r4"

inherit module pkgconfig

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "omap-4430sdp"

PACKAGES = "${PN} ${PN}-dbg"

S = "${WORKDIR}/git/"

PV = "0.0+git+${SRCREV}"

SRC_URI = "git://dev.omapzoom.org/pub/scm/pradeep/wlan-1283.git;protocol=git;branch=master \
          file://makefile-ar.patch;patch=1 \
          file://wlan.init \
"

do_compile() {
	cd ${S}/wilink7/platforms/os/linux
	cp ${STAGING_DIR_TARGET}/fw/firmware.bin ${S}/wilink7/platforms/os/linux/.

	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

    make ARCH=arm HOST_PLATFORM=sdc4430 KERNEL_DIR=${STAGING_KERNEL_DIR} \
    BUILD_SUPPL=n CROSS_COMPILE=${TARGET_PREFIX} clean

	make ARCH=arm HOST_PLATFORM=sdc4430 KERNEL_DIR=${STAGING_KERNEL_DIR} \
	BUILD_SUPPL=n CROSS_COMPILE=${TARGET_PREFIX} all
}

do_install() {
	install -d ${D}/wlan
	install -m 755 ${S}/wilink7/platforms/os/linux/firmware.bin ${D}/wlan
	install -m 0644 ${S}/wilink7/external_drivers/sdc4430/Linux/standard_sdio/sdio.ko ${D}/wlan
	install -m 0644 ${S}/wilink7/platforms/os/linux/tiwlan_drv.ko ${D}/wlan
	install -m 755 ${S}/wilink7/platforms/os/linux/tiwlan.ini ${D}/wlan
	install -m 755 ${S}/wilink7/platforms/os/linux/tiwlan_loader ${D}/wlan
	install -m 755 ${S}/wilink7/platforms/os/linux/wlan_cu ${D}/wlan

	install -d ${D}/etc/init.d
	install -d ${D}/etc/rc0.d
	install -d ${D}/etc/rc1.d
	install -d ${D}/etc/rc5.d
	install -d ${D}/etc/rc6.d
	
	install -m 755 ${FILESDIR}/wlan.init ${D}/etc/init.d/wlan
	
#	cd ${D}/etc/rc0.d && ln -s ../init.d/wlan K29Wlan
#	cd ${D}/etc/rc1.d && ln -s ../init.d/wlan K29Wlan
#	cd ${D}/etc/rc5.d && ln -s ../init.d/wlan S29Wlan
#	cd ${D}/etc/rc6.d && ln -s ../init.d/wlan K29Wlan
}

FILES_${PN} = "\
	/etc/*/* \
	/wlan/firmware.bin \
	/wlan/sdio.ko \
	/wlan/tiwlan_drv.ko \
	/wlan/tiwlan.ini \
	/wlan/tiwlan_loader \
	/wlan/wlan_cu \
	"
FILES_${PN}-dbg = "/wlan/.debug/*.ko"

