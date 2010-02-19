DEPENDS = "gstreamer"
SECTION = "libs"
DESCRIPTION = "Test and Trace InterFace"
PR = "r4"

SRC_URI = "\
	svn://plato.googlecode.com/svn/trunk/nonjava/target;module=${PN};rev=14454;proto=http \
	file://ttifdaemon.init \
	"

S = ${WORKDIR}/${PN}

#inherit autotools pkgconfig module update-rc.d
inherit autotools pkgconfig update-rc.d

do_stage() {
	autotools_stage_all
}

INITSCRIPT_NAME = "ttifdaemon"
INITSCRIPT_PARAMS = "start 01 5 ."

do_install_prepend() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${FILESDIR}/ttifdaemon.init ${D}${sysconfdir}/init.d/ttifdaemon
}

do_compile_prepend() {
#	oe_runmake CROSS_COMPILE=${AR%-*}- -C ${STAGING_KERNEL_DIR} M=${S}
#	oe_runmake CROSS_COMPILE=${AR%-*}- -C ${STAGING_KERNEL_DIR} M=${S}/test
}

do_install_prepend() {
#	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/ttif
#	install -m 0644 libttif.ko test/ttiftest.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/ttif
}
