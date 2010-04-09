DESCRIPTION = "FM radio test app"
SECTION = "console/multimedia"
PRIORITY = "optional"
DEPENDS = "alsa-utils bluez-libs"
RDEPENDS = "btfm"
PR = "r0"

inherit ccasefetch

COMPATIBLE_MACHINE = "omap-3430sdp|omap-3430ldp|omap-3630sdp|zoom2|zoom3"

CCASE_SPEC =   "%\
	element /vobs/WCGDev/... LINUX_BT_L${PV}%\
	"

CCASE_PATHFETCH = "/vobs/WCGDev/linux/"
CCASE_PATHCOMPONENT = "linux"
CCASE_PATHCOMPONENTS = "2"

PACKAGES = "${PN} ${PN}-dbg"

do_compile() {
        cp ${S}/build/makefile kfmapp/.
	install -m 755 ${S}/build/makefile kfmapp/makefile
	cd kfmapp
	oe_runmake CROSS=${AR%-*}- kfmapp
}

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 755 ${S}/init_scripts/fmc_ch8_1273.2.bts ${D}${base_libdir}/firmware
	install -m 755 ${S}/init_scripts/fm_rx_ch8_1273.2.bts ${D}${base_libdir}/firmware
 	install -m 755 ${S}/init_scripts/fm_tx_ch8_1273.2.bts ${D}${base_libdir}/firmware
      
 	install -d ${D}/usr/bin
	install -m 755 ${S}/kfmapp/kfmapp ${D}/usr/bin
}

FILES_${PN} = "\
		/lib/firmware/* \
		/usr/bin/kfmapp \
		"

FILES_${PN}-dbg = "\
		/usr/bin/.debug/kfmapp \
		"


