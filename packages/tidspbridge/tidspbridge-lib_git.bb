SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU Bridge API."
LICENSE = "GPL"
PR = "r0"
DEPENDS = " \
	linux-tiomap \
        "

PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"
FILES_${PN} += "${libdir}/libbridge.so ${libdir}/libqos.a"
FILES_${PN}-dev = "${libdir}/libbridge.so.2 ${libdir}/libqos.so.2 ${libdir}/lib*.so"
FILES_${PN}-dbg = "${libdir}/.debug"


inherit pkgconfig

S = "${WORKDIR}/git"

PV = "24.0+git+${SRCREV}"

SRC_URI = "git://dev.omapzoom.org/pub/scm/tisyslink/userspace-syslink.git;protocol=git"


do_compile_prepend() {
         install -m 0644 ${FILESDIR}/libdspbridge.pc ${S}/libdspbridge.pc 
}


do_compile() {

        cd ${S}/tesla_api/src
	oe_runmake \
                PREFIX=${S} PROJROOT=${S}/tesla_api \
                CROSS=${AR%-*}- -f Makefile clean

        oe_runmake \
                PREFIX=${S} PROJROOT=${S}/tesla_api \
                CROSS=${AR%-*}- -f Makefile

	oe_runmake  \
                PREFIX=${S} PROJROOT=${S}/tesla_api \
                CROSS=${AR%-*}- -f Makefile install

}

do_stage() {

	oenote "Installing code from mpu_api to ${STAGING_INCDIR}/dspbridge"
	install -d ${STAGING_INCDIR}/dspbridge
	install -D ${S}/tesla_api/inc/*.h ${STAGING_INCDIR}/dspbridge/

	oenote "Installing libraries in ${STAGING_LIBDIR} "
	oe_libinstall -so -C ${S}/target/lib libbridge ${STAGING_LIBDIR}
	oe_libinstall -so -a -C ${S}/target/lib libqos ${STAGING_LIBDIR}

	oenote "Installing libraries in ${STAGING_INCDIR}/dspbridge/lib "
	#if [ ! -d ${STAGING_INCDIR}/dspbridge/lib ]
	#then
	#	install -d ${STAGING_INCDIR}/dspbridge/lib
	#fi
	#oe_libinstall -so -C ${S}/target/lib libbridge ${STAGING_INCDIR}/dspbridge/lib
        #oe_libinstall -so -a -C ${S}/target/lib libqos ${STAGING_INCDIR}/dspbridge/lib

}

do_install() {
	oenote "Installing bridge libraries in ${D}/lib "
	oe_libinstall -so -C ${S}/target/lib libbridge ${D}${libdir}
	oe_libinstall -so -a -C ${S}/target/lib libqos ${D}${libdir}
}
