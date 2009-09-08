DESCRIPTION = "Texas Instruments OpenMAX IL AUDIO Decoder."
DEPENDS = "alsa-lib tidspbridge-mpusamples tidspbridge-syslinklib"
DEPENDS += "tiopenmax-git tiopenmax-core tiopenmax-lcml tiopenmax-mm-osal"
DEPENDS += "tiopenmax-base"
RDEPENDS = "alsa-utils-aplay"
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-patterns ${PN}-dev ${PN}"


require tiopenmax-cspec-${PV}.inc

CCASE_PATHFETCH = "\
	/vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/audio_decode \
	/vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/mp3_dec \
	/vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/aac_dec \
	/vobs/wtbu/OMAPSW_MPU/linux/Makefile \
	/vobs/wtbu/OMAPSW_MPU/linux/Master.mk \
	"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"


inherit ccasefetch

do_compile_prepend() {
	install -d ${D}/usr/omx/patterns
	install -d ${D}/usr/lib
	install -d ${D}/usr/bin
	rm -rf audio/src/openmax_il/audio_decode
        tar -C ${STAGING_BINDIR}/audio-omx/audio/src/openmax_il -cvf - audio_decode | tar -C audio/src/openmax_il -xvf -
	rm -rf audio/src/openmax_il/mp3_dec
        tar -C ${STAGING_BINDIR}/audio-omx/audio/src/openmax_il -cvf - mp3_dec | tar -C audio/src/openmax_il -xvf -
	rm -rf audio/src/openmax_il/aac_dec
	tar -C ${STAGING_BINDIR}/audio-omx/audio/src/openmax_il -cvf - aac_dec | tar -C audio/src/openmax_il -xvf -

}

ENV_VARS = "PKGDIR=${S} \
            OMXROOT=${S} \
            CROSS=${AR%-*}- \
            OMX_RESOURCEMANAGER_DISABLE=1"

do_compile() {


	install -d ${D}/include
        tar -C ${STAGING_INCDIR}/dspbridge -cvf - omx | tar -C ${D}/include -xvf -
	oenote "Extracting ALSA Files"
	install -d ${D}/usr/include/alsa
        tar -C ${STAGING_INCDIR} -cvf - alsa | tar -C ${D}/usr/include -xvf -

	oenote "Compiling Audio decoder and tiomxplayer..."

	cd ${S}/audio/src/openmax_il/audio_decode

	${ENV_VARS} oe_runmake \
		PREFIX=${D}/usr \
		BRIDGEINCLUDEDIR=${STAGING_INCDIR}/dspbridge \
		BRIDGELIBDIR=${STAGING_LIBDIR} \
		TARGETDIR=${D}/usr OMXTESTDIR=${D}${bindir} \
		OMXLIBDIR=${STAGING_LIBDIR} \
		OMXINCLUDEDIR=${STAGING_INCDIR}/omx \
		clean all
}

do_install() {
	oenote "Installing MM Audio libraries..."
	oe_libinstall -so -C ${STAGING_LIBDIR} libOMX.TI.AUDIO.DECODE ${D}/usr/lib
	oe_libinstall -so -C ${STAGING_LIBDIR} libLCML ${D}/usr/lib
	oe_libinstall -so -C ${STAGING_LIBDIR} libOMX_Base ${D}/usr/lib
	oe_libinstall -so -C ${STAGING_LIBDIR} libOMX_Core ${D}/usr/lib
	oe_libinstall -so -C ${STAGING_LIBDIR} libmmosal ${D}/usr/lib
	#oe_libinstall -so -C ${STAGING_LIBDIR} libasound ${D}/usr/lib
	oenote "Installing tiomxplayer..."
	install -d ${D}/usr/bin
	install -m 0755 ${S}/audio/src/openmax_il/audio_decode/tests/tiomxplayer ${D}/usr/bin/
	oenote "Installing mp3 and aac baseimages..."
	install -d ${D}/dspbridge/audio_images
	install -m 0644 ${STAGING_BINDIR}/audio-omx/binaries/baseimage_aac_tiomap4430.dof64T ${D}/dspbridge/audio_images/
	install -m 0644 ${STAGING_BINDIR}/audio-omx/binaries/baseimage_mp3_tiomap4430.dof64T ${D}/dspbridge/audio_images/
}

do_stage() {
	install -m 0755 ${D}/usr/bin/tiomxplayer ${STAGING_BINDIR}
}

FILES_${PN} = "\
	/usr/lib \
	/usr/bin \
	/dspbridge/audio_images \
	"

FILES_${PN}-patterns = "\
	/usr/omx/patterns \
	"

FILES_${PN}-dbg = "\
	/usr/bin/.debug \
	/usr/lib/.debug \
	"

FILES_${PN}-dev = "\
	/usr/include \
	"

do_stage_rm_omxdir() {
	# Clean up undesired staging only if test patterns exist
	${@base_contains("DISTRO_FEATURES", "testpatterns", "rm -rf ${STAGING_DIR_TARGET}/usr/omx/", "echo nothing to do here!", d)}
}

do_install_cleanup() {
	# move test files out of /usr/bin/ to /usr/omx only if test patterns exist
	${@base_contains("DISTRO_FEATURES", "testpatterns", "mv ${D}${bindir}/* ${D}/usr/omx/patterns", "echo nothing to do here!", d)}
}

addtask install_cleanup after do_install before do_package
addtask stage_rm_omxdir after do_populate_staging before do_package_stage
