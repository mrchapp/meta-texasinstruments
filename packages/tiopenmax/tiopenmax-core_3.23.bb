#DEPENDS = "tidspbridge-lib tiopenmax-perf tiopenmax-common"
DEPENDS = "tidspbridge-mpusamples tidspbridge-syslinklib tiopenmax-git"
DESCRIPTION = "Texas Instruments OpenMAX IL Core."
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"
FILES_${PN} = "${libdir}/lib*.so.*"

require tiopenmax-cspec-${PV}.inc

CCASE_PATHFETCH = "\
	/vobs/wtbu/OMAPSW_MPU/linux/system/src/openmax_il \
	/vobs/wtbu/OMAPSW_MPU/linux/Makefile \
	/vobs/wtbu/OMAPSW_MPU/linux/Master.mk \
	"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"

SRC_URI = "\
	file://libomxil-ti.pc \
	"

inherit pkgconfig ccasefetch

do_compile_prepend() {
	
	install -m 0644 ${FILESDIR}/libomxil-ti.pc ${S}/libomxil.pc
	install -d ${D}${libdir}
	rm -rf system/src/openmax_il/omx_core
	tar -C ${STAGING_BINDIR}/audio-omx/system/src/openmax_il -cvf - omx_core | tar -C system/src/openmax_il -xvf -
}

do_compile() {

	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D}/usr OMXROOT=${S} \
		core
}

do_install() {
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D}/usr OMXROOT=${S} \
		core.install
}

do_stage() {
	oe_runmake \
		PREFIX=${STAGING_DIR_TARGET}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${STAGING_DIR_TARGET}/usr OMXROOT=${S} \
		core.install

	install -m 0644 ${D}/usr/include/omx/*.h ${STAGING_INCDIR}/omx/
	install -m 0644 system/src/openmax_il/resource_manager_proxy/inc/*.h ${STAGING_INCDIR}/omx/
}

FILES_${PN} = "\
	/usr/lib \
	"

FILES_${PN}-dbg = "\
	/usr/lib/.debug \
	"

FILES_${PN}-dev = "\
	/usr/include \
	"
