DEPENDS = "tiopenmax-git tiopenmax-core tiopenmax-mm-osal"
DESCRIPTION = "Texas Instruments Base for OpenMAX IL."
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

require tiopenmax-cspec-${PV}.inc

CCASE_PATHFETCH = "\
        /vobs/wtbu/OMAPSW_MPU/linux/Makefile \
        /vobs/wtbu/OMAPSW_MPU/linux/Master.mk \
        "
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "linux"


inherit ccasefetch

do_compile_prepend() {
        install -d ${D}/usr/omx
        install -d ${D}/usr/lib
        install -d ${D}/usr/bin
	install -d system/src/openmax_il
        tar -C ${STAGING_BINDIR}/audio-omx/system/src/openmax_il -cvf - omx_base | tar -C system/src/openmax_il -xvf -
	tar -C ${STAGING_BINDIR}/audio-omx/system/src/openmax_il -cvf - mm_osal/inc | tar -C system/src/openmax_il -xvf -
}

do_compile() {

        if [ ! -d ${S}/target/lib ]
        then
                install -d ${S}/target/lib
        fi
        cp -a ${STAGING_LIBDIR}/libOMX_Core.so ${S}/target/lib
	cp -a ${STAGING_LIBDIR}/libmmosal.so ${S}/target/lib

	install -d ${S}/target/include/omx

        cd system/src/openmax_il/omx_base
        oe_runmake \
                PKGDIR=${S} \
                CROSS=${AR%-*}- \
                OMXROOT=${S} \
                OMXINCLUDEDIR=${STAGING_INCDIR}/omx \
                OMX_RESOURCEMANAGER_DISABLE=1 \
                clean all
}

do_install() {

        oe_libinstall -so -C ${S}/target/lib libOMX_Base ${D}${libdir}

	install -d ${D}/usr/include/omx
	cp -prf ${S}/system/src/openmax_il/omx_base/inc/*.h ${D}/usr/include/omx/

}

do_stage() {

        oe_libinstall -so -C ${S}/target/lib libOMX_Base ${STAGING_LIBDIR}
	cp -prf ${S}/target/include/omx*  ${STAGING_INCDIR}/dspbridge/omx
}

FILES_${PN} = "\
        /usr/lib \
        /usr/bin \
        "
#       /usr/omx \

FILES_${PN}-dbg = "\
        /usr/bin/.debug \
        /usr/lib/.debug \
        "
#       /usr/omx/.debug \

FILES_${PN}-dev = "\
        /usr/include \
        "

