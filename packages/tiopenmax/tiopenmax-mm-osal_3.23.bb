DEPENDS = "tiopenmax-git tiopenmax-core"
DESCRIPTION = "Texas Instruments OSAL for OpenMAX IL."
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
        tar -C ${STAGING_BINDIR}/audio-omx/system/src/openmax_il -cvf - mm_osal | tar -C system/src/openmax_il -xvf -
}

do_compile() {

        if [ ! -d ${S}/target/lib ]
        then
                install -d ${S}/target/lib
        fi
        cp -a ${STAGING_LIBDIR}/libOMX_Core.so ${S}/target/lib
        cd system/src/openmax_il/mm_osal
        oe_runmake \
                PKGDIR=${S} \
                CROSS=${AR%-*}- \
                OMXROOT=${S} \
                OMXINCLUDEDIR=${STAGING_INCDIR}/omx \
                OMX_RESOURCEMANAGER_DISABLE=1 \
                clean all
}

do_install() {

        oe_libinstall -so -C ${S}/target/lib libmmosal ${D}/{libdir}
}

do_stage() {

        oe_libinstall -so -C ${S}/target/lib libmmosal ${STAGING_LIBDIR}
	install -m 0644 ${S}/system/src/openmax_il/mm_osal/inc/*.h ${STAGING_INCDIR}/omx/

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

