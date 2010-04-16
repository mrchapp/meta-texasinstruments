DESCRIPTION = "Bluetooth and FM modules for OMAP"
SECTION = "libs"
PRIORITY = "optional"
RDEPENDS = "expat dbus bluez-libs bluez-utils bluez-utils-alsa openobex obexftp"
PR = "r0"

inherit ccasefetch

COMPATIBLE_MACHINE = "omap-3430(l|s)dp|omap-3630sdp|zoom2|zoom3"

CCASE_SPEC =   "%\
        element /vobs/WCGDev/... LINUX_BT_L${PV} %\
	"

CCASE_PATHFETCH = "/vobs/WCGDev/linux/"
CCASE_PATHCOMPONENT = "linux"
CCASE_PATHCOMPONENTS = "2"

PACKAGES = "${PN} ${PN}-dbg"

do_compile() {
	tr -d '\015' < bluetooth_init.sh > ti_btfm_init
	cp ${S}/build/makefile uim/.
	cd uim
	oe_runmake CROSS=${AR%-*}- uim
}

do_install() {
	install -d ${D}${base_libdir}/firmware
	install -m 755 ${S}/init_scripts/TIInit_7.1.24.bts ${D}${base_libdir}/firmware
	install -m 755 ${S}/init_scripts/TIInit_7.1.24.bts.3000000 ${D}${base_libdir}/firmware
        install -m 755 ${S}/init_scripts/TIInit_7.2.31.bts ${D}${base_libdir}/firmware

	install -d ${D}/usr/bin
	install -m 755 ${S}/uim/uim ${D}/usr/bin
	
	install -d ${D}/etc/init.d
	install -m 755 ${S}/ti_btfm_init ${D}/etc/init.d/ti_btfm_init
}

FILES_${PN} = "\
		/lib/firmware/* \
		/usr/bin/uim \
		/etc/init.d/ti_btfm_init \
		"

FILES_${PN}-dbg = "\
		/usr/bin/.debug/uim \
		"
