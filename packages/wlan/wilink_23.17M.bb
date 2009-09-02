#SECTION = ""
DESCRIPTION = "WLAN stack (kernel module, libs, wpa_supplicant)"
PR = "r1"
# Note:	This recipe will be broken into many recipes for future releases
# 	For now this mimics the MCS layout and mini-build system in ClearCase

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp|omap-zoom2-(alpha|beta)"

CCASE_SPEC = "%\
	element /vobs/WiLink/... LINUX-WCG-WLAN_RLS_L23-17-M1%\
	element /vobs/WCGDev/... LINUX-WCG-WLAN_RLS_L23-17-M1%\
	"

CCASE_PATHFETCH = "/vobs/WiLink/ \
	/vobs/WCGDev \
	"
CCASE_PATHCOMPONENTS = 0
CCASE_PATHCOMPONENT = "vobs"

SRC_URI = "file://fix-libestadrv-makefile.diff;patch=1\
	file://fix-libuadrv-makefile.diff;patch=1\
	file://fix-CUDK-makefile.diff;patch=1\
	file://remove-hald-reconfig-wlan-script.diff;patch=1\
	"

PACKAGES = "${PN}"
FILES_${PN} += "/wlan /etc"

# nasty hack for the moment.  Will fix this in upstream makefiles for L23.13.
do_configure () {
	sed -i s#/vobs#${S}# \
		${S}/WiLink/external_drivers/omap3430/Linux/sdio/Makefile
}

do_compile () {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	cd ${S}/WiLink/platforms/os/linux
	make CROSS_COMPILE=${AR%-*}- ARCH=arm HOST_PLATFORM=omap3430 KERNEL_DIR=${STAGING_KERNEL_DIR} BUILD_SUPPL=n clean
	make CROSS_COMPILE=${AR%-*}- ARCH=arm HOST_PLATFORM=omap3430 KERNEL_DIR=${STAGING_KERNEL_DIR} BUILD_SUPPL=n
}

do_install() {
	install -d ${D}/wlan

#CHANGES done for incorporating WLAN startup script for GNOME

#Create the directories if they don't already exist
	install -d ${D}/etc/init.d
	install -d ${D}/etc/rc0.d
	install -d ${D}/etc/rc1.d
	install -d ${D}/etc/rc2.d
	install -d ${D}/etc/rc3.d
	install -d ${D}/etc/rc4.d
	install -d ${D}/etc/rc5.d
	install -d ${D}/etc/rc6.d

	install -m 755 ${S}/WiLink/CUDK/wlan ${D}/etc/init.d/
	cd ${D}/etc/rc0.d && ln -s ../init.d/wlan K29Wlan
	cd ${D}/etc/rc1.d && ln -s ../init.d/wlan K29Wlan
	cd ${D}/etc/rc2.d && ln -s ../init.d/wlan S29Wlan
	cd ${D}/etc/rc3.d && ln -s ../init.d/wlan S29Wlan
	cd ${D}/etc/rc5.d && ln -s ../init.d/wlan S29Wlan
	cd ${D}/etc/rc6.d && ln -s ../init.d/wlan K29Wlan

#CHANGES end

	install -m 755 ${S}/WiLink/platforms/os/linux/wlan_cu ${D}/wlan
	install -m 755 ${S}/WiLink/platforms/os/linux/tiwlan.ini ${D}/wlan
	install -m 755 ${S}/WiLink/platforms/os/linux/tiwlan_loader ${D}/wlan

	install -m 644 ${S}/WiLink/platforms/os/linux/tiwlan_drv.ko ${D}/wlan
	install -m 644 ${S}/WiLink/platforms/os/linux/firmware.bin ${D}/wlan

	install -m 644 ${S}/WiLink/external_drivers/omap3430/Linux/sdio/sdio.ko\
		${D}/wlan
}
