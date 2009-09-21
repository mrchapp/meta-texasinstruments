SECTION="libs"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Tiler Library and Test tool."
LICENSE = "GPL"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

FILES_${PN}-dbg += "/usr/bin/.debug"
FILES_${PN}-dbg += "${libdir}/.debug"
FILES_${PN}-dev += "${libdir}/lib*.so*"
FILES_${PN} +="${libdir}/lib*.so"
FILES_${PN} +="${bindir}"
FILES_${PN} +="/usr/include"

# L24.0.11 tag
SRCREV = "L24_0_11"


SRC_URI = "\
		git://gforge01.dal.design.ti.com/omap4vidtestapp;protocol=git \
        "

S = "${WORKDIR}/git"


do_compile() {

	cd ${S}/tiler/user
	install -d ${S}${libdir}
	install -d ${S}/usr/include
	install -d ${S}${bindir}

	oe_runmake \
		LIBDIR=${S}${libdir} \
		INCDIR=${S}/usr/include \
                CROSS=${AR%-*}- 

	oe_runmake \
                LIBDIR=${S}${libdir} \
                INCDIR=${S}/usr/include \
                CROSS=${AR%-*}- install

	cd ${S}/test/tiler

	oe_runmake \
                BINDIR=${S}${bindir} \
                LIBDIR=${S}${libdir} \
		SHAREDINCDIR=${S}/usr/include \
		CROSS=${AR%-*}-  distclean

	oenote "Compiling test tool..."
	#Trick because makefile Include var is ignored
	cp ${S}/usr/include/tilermgr.h .

	oe_runmake \
		BINDIR=${S}${bindir} \
		LIBDIR=${S}${libdir} \
		SHAREDINCDIR=${S}/usr/include \
                CROSS=${AR%-*}- 

	oenote "Installing test tool..."

	oe_runmake \
                BINDIR=${S}${bindir} \
                LIBDIR=${S}${libdir} \
                SHAREDINCDIR=${S}/usr/include \
		CROSS=${AR%-*}- install

}

do_install() {
	install -d ${D}${bindir}
	install -D ${S}${bindir}/dmmdrvtest ${D}${bindir}
	install -d ${D}/usr/include
	install -D ${S}/usr/include/tilermgr.h ${D}/usr/include/
	install -d ${D}${libdir}
	#oe_libinstall -so -C ${STAGING_LIBDIR} libdmmdrv ${D}${libdir}
	oe_libinstall -so -C ${S}${libdir} libdmmdrv ${D}${libdir}
}

do_stage() {
        install -D ${S}${bindir}/dmmdrvtest ${STAGING_BINDIR}
        install -D ${S}/usr/include/tilermgr.h ${STAGING_INCDIR}
        oe_libinstall -so -C ${S}${libdir} libdmmdrv ${STAGING_LIBDIR}
}

addtask stage before do_install after do_compile
