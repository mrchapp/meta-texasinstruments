DEPENDS = "tidspbridge-lib mm-isp ${TTIF_DEPENDS}"
DESCRIPTION = "Texas Instruments OpenMAX IL."
PR = "r2"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}-patterns ${PN}"

PROVIDES = "virtual/openmax-il"
RPROVIDES = "virtual/openmax-il"

require tiopenmax.inc
require tiopenmax-cspec-${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_MPU/linux"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"


inherit pkgconfig

SRC_URI = "\
	file://libomxil-ti.pc \
	file://ampthread.patch;patch=1 \
	file://pmpthread.patch;patch=1 \
	file://23.10-rmmake.patch;patch=1 \
	file://pcmencmk.patch;patch=1 \
	file://wmadecmk.patch;patch=1 \
	file://g722encmk.patch;patch=1 \
	file://23.11-vppmake.patch;patch=1 \
	file://videodecmk.patch;patch=1 \
	file://23.11-cameramk.patch;patch=1 \
	${@base_contains("DISTRO_FEATURES", "720p", "file://23.12-armaacnopatterns.patch;patch=1", "", d)} \
	${@base_contains("DISTRO_FEATURES", "rarv", "file://23.13-radectestmk.patch;patch=1", "", d)} \
	${@base_contains("DISTRO_FEATURES", "rarv", "file://23.13-rvdecmk.patch;patch=1", "", d)} \
	${@base_contains("DISTRO_FEATURES", "rarv", "", "file://remove-rarv.patch;patch=1", d)} \
	${@base_contains("DISTRO_FEATURES", "testpatterns", "", "file://remove-patterns.patch;patch=1", d)} \
	file://include-path-fixes.patch;patch=1 \
	file://viddec-rowstride.patch;patch=1 \
	"

SRC_URI += " ${@base_contains("DISTRO_FEATURES", "ttif", "file://ttif.patch;patch=1", "", d)} "

do_compile_prepend() {
	install -m 0644 ${FILESDIR}/libomxil-ti.pc ${S}/libomxil.pc
	install -d ${D}${libdir}
	install -d ${D}/usr/include/mmisp
	cp ${STAGING_INCDIR}/capl/inc/*.h ${S}/video/src/openmax_il/camera/inc
	cp ${STAGING_INCDIR}/camera_algo_frmwk/inc/*.h ${S}/video/src/openmax_il/camera/inc
	cp ${STAGING_INCDIR}/ipp/inc/*.h ${S}/video/src/openmax_il/camera/inc
	cp ${STAGING_INCDIR}/mmisp/*.h ${D}/usr/include/mmisp
}


