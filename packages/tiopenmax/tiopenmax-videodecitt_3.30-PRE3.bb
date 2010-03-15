DESCRIPTION = "Ittiam OpenMAX IL Video Decoder."
DEPENDS = "tidspbridge-lib tiopenmax-core tiopenmax-lcml tiopenmax-rmproxy tiopenmax-resourcemanager"
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

require tiopenmax-cspec-${PV}.inc

CCASE_PATHFETCH = "\
	/vobs/wtbu/OMAPSW_MPU/linux/video/src/openmax_il/video_decode_ittiam \
	/vobs/wtbu/OMAPSW_MPU/linux/Makefile \
	/vobs/wtbu/OMAPSW_MPU/linux/Master.mk \
	"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"

# Patch to allow fwrite when using VidDecTest_common_Itt
#SRC_URI = " \
#       file://enable-fwrite.patch;patch=1 \
#       "

inherit ccasefetch

do_compile_prepend() {
	install -d ${D}/usr/omx
	install -d ${D}/usr/lib
	install -d ${D}/usr/bin
}

do_compile() {
	cd ${S}/video/src/openmax_il/video_decode_ittiam
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D}/usr OMXTESTDIR=${D}${bindir} OMXROOT=${S} OMXLIBDIR=${STAGING_LIBDIR} \
		OMX_PERF_INSTRUMENTATION=1 OMX_PERF_CUSTOMIZABLE=1 \
		OMXINCLUDEDIR=${STAGING_INCDIR}/omx \
		all
}

do_install() {
	cd ${S}/video/src/openmax_il/video_decode_ittiam
	oe_runmake \
		PREFIX=${D}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D}/usr OMXTESTDIR=${D}${bindir} OMXROOT=${S} \
		OMX_PERF_INSTRUMENTATION=1 OMX_PERF_CUSTOMIZABLE=1 \
		SYSTEMINCLUDEDIR=${D}/usr/include/omx \
		install
}

do_stage() {
	cd ${S}/video/src/openmax_il/video_decode_ittiam
	oe_runmake \
		PREFIX=${STAGING_DIR_TARGET}/usr PKGDIR=${S} \
		CROSS=${AR%-*}- \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${STAGING_DIR_TARGET}/usr OMXTESTDIR=${STAGING_BINDIR} OMXROOT=${S} \
		OMX_PERF_INSTRUMENTATION=1 OMX_PERF_CUSTOMIZABLE=1 \
		SYSTEMINCLUDEDIR=${STAGING_INCDIR}/omx \
		install
}

FILES_${PN} = "\
	/usr/lib \
	/usr/bin \
	"

FILES_${PN}-dbg = "\
	/usr/bin/.debug \
	/usr/lib/.debug \
	"

FILES_${PN}-dev = "\
	/usr/include/omx \
	"
