DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
PR = "r1"
DEPENDS = "linux-tiomap sysvinit"

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"

SRC_URI += " \
	file://l23.init \
         "

do_install() {
	install -d ${D}/etc/init.d
	install -d ${D}/etc/rc0.d
	install -d ${D}/etc/rc1.d
	install -d ${D}/etc/rc2.d
	install -d ${D}/etc/rc3.d
	install -d ${D}/etc/rc4.d
	install -d ${D}/etc/rc5.d
	install -d ${D}/etc/rc6.d
	
	install -m 755 ${FILESDIR}/l23.init ${D}/etc/init.d/l23.init
	
	cd ${D}/etc/rc0.d && ln -s ../init.d/l23.init K90L23Init
	cd ${D}/etc/rc1.d && ln -s ../init.d/l23.init K90L23Init
	cd ${D}/etc/rc5.d && ln -s ../init.d/l23.init S90L23Init
	cd ${D}/etc/rc6.d && ln -s ../init.d/l23.init K90L23Init
}

FILES_${PN} = "\
	/etc/*/* \
	"
