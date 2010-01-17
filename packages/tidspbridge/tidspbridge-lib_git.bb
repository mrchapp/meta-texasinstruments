SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
LICENSE = "GPL"
PR = "r1"
DEPENDS = " \
	linux-tiomap \
	"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
FILES_${PN} = "${libdir}/libbridge.so ${libdir}/libbridge.so.2 ${libdir}/libqos.a ${libdir}/libqos.so.2"
FILES_${PN}-dev = "${includedir}/dspbridge"


inherit pkgconfig

S = "${WORKDIR}/git"

PV = "23.0+git+${SRCREV}"

SRC_URI = "git://dev.omapzoom.org/pub/scm/tidspbridge/userspace-dspbridge.git;protocol=git;branch=master"

do_compile() {

	cd ${S}/source
	oe_runmake  KRNLSRC=${STAGING_KERNEL_DIR}  \
		 DEPOT=${STAGING_BINDIR_NATIVE}/dspbridge/tools .api

}

do_stage() {
	oe_libinstall -so -C ${S}/source/target/lib libbridge ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/source/target/lib libqos ${STAGING_LIBDIR}
	install -d ${STAGING_INCDIR}/dspbridge
	install -m 0644 ${S}/source/mpu_api/inc/*.h ${STAGING_INCDIR}/dspbridge/
}

do_install() {
	oe_libinstall -so -C ${S}/source/target/lib libbridge ${D}/${libdir}
	oe_libinstall -so -C ${S}/source/target/lib libqos ${D}/${libdir}
	install -d ${D}${includedir}/dspbridge
	install -m 0644 ${S}/source/mpu_api/inc/*.h ${D}/${includedir}/dspbridge/
}
