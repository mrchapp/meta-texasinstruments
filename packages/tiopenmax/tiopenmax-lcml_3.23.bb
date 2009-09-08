DEPENDS = "tidspbridge-mpusamples tidspbridge-syslinklib tiopenmax-git tiopenmax-core"
DESCRIPTION = "Texas Instruments Linux Common Media Library for OpenMAX IL."
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

require tiopenmax-cspec-${PV}.inc

CCASE_PATHFETCH = "\
	/vobs/wtbu/OMAPSW_MPU/linux/system/src/openmax_il/lcml \
	/vobs/wtbu/OMAPSW_MPU/linux/Makefile \
	/vobs/wtbu/OMAPSW_MPU/linux/Master.mk \
	"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"

SRC_URI = "file://23.11-lcmlnocore.patch;patch=1"

inherit ccasefetch

do_compile_prepend() {
	install -d ${D}/usr/omx
	install -d ${D}/usr/lib
	install -d ${D}/usr/bin
	rm -rf system/src/openmax_il/lcml
	tar -C ${STAGING_BINDIR}/audio-omx/system/src/openmax_il -cvf - lcml | tar -C system/src/openmax_il -xvf -
}

do_compile() {

	install -d ${S}/target/include
	tar -C ${STAGING_INCDIR}/dspbridge/mpu_api -cvf - inc | tar -C ${S}/target/include -xvf -
	mv ${S}/target/include/inc ${S}/target/include/dspbridge
	install -d ${S}/target/include/dspbridge/lib
	cp -a ${STAGING_LIBDIR}/libbridge.* ${S}/target/include/dspbridge/lib
	if [ ! -d ${S}/target/lib ]
	then
		install -d ${S}/target/lib
	fi
	oe_libinstall -so -C ${STAGING_LIBDIR} libbridge ${S}/target/lib
	oe_libinstall -so -C ${STAGING_LIBDIR} libOMX_Core ${S}/target/lib

	cd system/src/openmax_il/lcml
	oe_runmake \
		PKGDIR=${S} \
		CROSS=${AR%-*}- \
		OMXROOT=${S} \
		OMXINCLUDEDIR=${STAGING_INCDIR}/omx \
		OMX_RESOURCEMANAGER_DISABLE=1 \
		clean all
}

do_install() {

	oe_libinstall -so -C ${S}/target/lib libLCML ${D}/${libdir}
}

do_stage() {

	oe_libinstall -so -C ${S}/target/lib libLCML ${STAGING_LIBDIR}
	install -m 0555 ${S}/target/include/dspbridge/*.h ${STAGING_INCDIR}/dspbridge
}

FILES_${PN} = "\
	/usr/lib \
	/usr/bin \
	"
#	/usr/omx \

FILES_${PN}-dbg = "\
	/usr/bin/.debug \
	/usr/lib/.debug \
	"
#	/usr/omx/.debug \

FILES_${PN}-dev = "\
	/usr/include \
	"
