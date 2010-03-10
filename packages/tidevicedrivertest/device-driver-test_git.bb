DESCRIPTION = "Texas Instruments Device Driver Test Suite"
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "virtual/kernel"
inherit  pkgconfig

PR = "r1"
PV = "0.00+git+${SRCREV}"

SRC_URI = "git://dev.omapzoom.org/pub/scm/richo/device_driver_test.git;protocol=git "

S = "${WORKDIR}/git/"

PACKAGES = "${PN} ${PN}-dbg"
FILES_${PN} = "/testsuites/*/binaries/* /testsuites/*/bin/* /testsuites/*/lib/*.so /testsuites/*/scripts/*" 
FILES_${PN}-dbg += "/testsuites/*/binaries/.debug /testsuites/*/bin/.debug /testsuites/*/lib/.debug /testsuites/*/scripts/.debug"

do_compile() {
	export KDIR=${STAGING_KERNEL_DIR}
	export CROSS_COMPILE=${TARGET_PREFIX}
	export HOST=${TARGET_PREFIX}
	export ARCH=arm
	export TESTSUITES="audio-alsa audio-oss battery cachebench camera combo demo dma ethernet framebuffer gpio hdq hsuart i2c IrDA keypad lmbench mcbsp mcspi mmc mtd nand neon norflash oskernel performance pmd power_management realtimeclock sdio security sound_services timer-32k touchscreen usb_device usb_ehci usb_host usb_ohci usb_otg utils video watchdog"
	${S}/buildTestSuites.sh ${S}
}

do_install() {
	cp -R ${S}/testsuites ${D}

	rm ${D}/testsuites/mmc/utils/e2fsprogs/misc/e4defrag
	rm ${D}/testsuites/usb_device/utils/applications/evtest
	rm ${D}/testsuites/*/*/evtest
	rm -rf ${D}/testsuites/video/scripts/helper
	rm -rf ${D}/testsuites/*/utils/mtd-utils/
}
