SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "WLAN stack (kernel module, libs, wpa_supplicant)"
DEPENDS = "linux-tiomap wilink-firmware wpa-supplicant"
LICENSE = "BSD"
PR = "r0"

inherit module pkgconfig

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp|omap-3630sdp|zoom2|zoom3"

PACKAGES = "${PN} ${PN}-dbg"

S = "${WORKDIR}/git"
PV = "23.i3+git+${SRCREV}"

SRC_URI = "git://dev.omapzoom.org/pub/scm/vijay/wlan.git;protocol=git;branch=master"
SRC_URI += " \
	file://wlan.init \
	file://make-ar-fix.patch;patch=1 \
         "
do_compile() {
	install -d ${S}/fw/Latest
	install -d ${S}/CUDK/output
	cd ${S}/platforms/os/linux
	cp ${STAGING_DIR_TARGET}/fw/firmware.bin ${S}/platforms/os/linux/.
    	cp ${STAGING_DIR_TARGET}/fw/Fw1273_CHIP.bin ${S}/fw/Latest/.

	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

	make ARCH=arm HOST_PLATFORM=${MACHINE} KERNEL_DIR=${STAGING_KERNEL_DIR} \
	TI_SUPP_LIB_DIR=${STAGING_INCDIR}/wpa-supplicant \
	BUILD_SUPPL=y CROSS_COMPILE=${TARGET_PREFIX} clean

	make ARCH=arm HOST_PLATFORM=${MACHINE} KERNEL_DIR=${STAGING_KERNEL_DIR} \ 
	TI_SUPP_LIB_DIR=${STAGING_INCDIR}/wpa-supplicant \
	BUILD_SUPPL=y CROSS_COMPILE=${TARGET_PREFIX}
}

do_install() {
	install -d ${D}/wlan
	install -m 755 ${S}/platforms/os/linux/firmware.bin ${D}/wlan
	install -m 0644 ${S}/external_drivers/zoom3/Linux/sdio/sdio.ko ${D}/wlan
	install -m 0644 ${S}/platforms/os/linux/tiwlan_drv.ko ${D}/wlan
	install -m 755 ${S}/platforms/os/linux/tiwlan.ini ${D}/wlan
	install -m 755 ${S}/platforms/os/linux/tiwlan_loader ${D}/wlan
	install -m 755 ${S}/platforms/os/linux/wlan_cu ${D}/wlan
	
	install -d ${D}/etc/init.d
	install -d ${D}/etc/rc0.d
	install -d ${D}/etc/rc1.d
	install -d ${D}/etc/rc2.d
	install -d ${D}/etc/rc3.d
	install -d ${D}/etc/rc4.d
	install -d ${D}/etc/rc5.d
	install -d ${D}/etc/rc6.d
	
	install -m 755 ${FILESDIR}/wlan.init ${D}/etc/init.d/wlan
	
	cd ${D}/etc/rc0.d && ln -s ../init.d/wlan K29Wlan
	cd ${D}/etc/rc1.d && ln -s ../init.d/wlan K29Wlan
	cd ${D}/etc/rc5.d && ln -s ../init.d/wlan S29Wlan
	cd ${D}/etc/rc6.d && ln -s ../init.d/wlan K29Wlan
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
