PRIORITY = "optional"
DESCRIPTION = "Texas Instruments OpenMAX IL VIDEO OMX test components."
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
LICENSE = "LGPL"
PR = "r0"

DEPENDS = " \
    tisyslink-lib \
    titiler-d2cmap \
    titiler-memmgr\
    tiopenmax-video-sample \
    "
inherit pkgconfig

PV = "0.0+git+${SRCREV}"

SRC_URI = "git://dev.omapzoom.org/pub/scm/video/video-omx.git;protocol=git \
    file://Makefile.patch;patch=1 \
    file://H264_Playback_ILClient.patch;patch=1 \
"

S = "${WORKDIR}/git"


FILES_${PN} += "/vidbinaries/*.out"
FILES_${PN}-dev += ""
FILES_${PN}-dbg += "/vidbinaries/.debug/*.out"


do_compile_prepend() {
    install -d ${S}/src
    cp -r -f ${STAGING_DIR}/video/src/* ${S}/src
    cp -r -f ${STAGING_DIR}/video/test/make ${S}/test
}

do_compile() {
    install -d ${S}/target
    install -d ${S}/target/lib


    oenote "Make again compilation of DOMX core libraries:"
    cd ${S}/src

    oenote "Cleaning DOMX src:"
    oe_runmake \
        PROJROOT=${S}/src \
        TILER_INC_PATH=${STAGING_INCDIR} \
        BRIDGEROOT=${S} \
                clean

    oenote "Compiling DOMX src:"
    oe_runmake \
        PROJROOT=${S}/src \
        TILER_INC_PATH=${STAGING_INCDIR} \
        BRIDGEROOT=${S}

    oenote "Installing DOMX src:"
    oe_runmake \
        PROJROOT=${S}/src \
        TILER_INC_PATH=${STAGING_INCDIR} \
        BRIDGEROOT=${S} \
                install

    oenote "Copying DOMX dynamic libraries:"
        cp ${STAGING_LIBDIR}/libutils.so ${S}/target/lib 
        cp ${STAGING_LIBDIR}/libprocmgr.so ${S}/target/lib 
        cp ${STAGING_LIBDIR}/libipc.so ${S}/target/lib 
        cp ${STAGING_LIBDIR}/librcm.so ${S}/target/lib 
        cp ${STAGING_LIBDIR}/libnotify.so ${S}/target/lib 
        cp ${STAGING_LIBDIR}/libsysmgr.so ${S}/target/lib 
        cp ${STAGING_LIBDIR}/libsysmemmgr.so ${S}/target/lib 
        cp ${STAGING_LIBDIR}/libmemmgr.so ${S}/target/lib 
        cp ${STAGING_LIBDIR}/libd2cmap.so ${S}/target/lib 

    oenote "Compilation of DOMX samples:"
    cd ${S}/test/omx/video_playback/h264dec

    oenote "Cleaning DOMX sample src:"
    oe_runmake \
        PROJROOT=${S}/test \
        TILER_INC_PATH=${STAGING_INCDIR} \
        BRIDGEROOT=${S} \
                clean

    oenote "Compiling DOMX sample src:"
    oe_runmake \
        PROJROOT=${S}/test \
        TILER_INC_PATH=${STAGING_INCDIR} \
        BRIDGEROOT=${S} \

    oenote "Installing DOMX sample src:"
    oe_runmake \
        PROJROOT=${S}/test \
        TILER_INC_PATH=${STAGING_INCDIR} \
        BRIDGEROOT=${S} \
                install

}
do_install() {
    oenote "Installing Ducati test application:"
    install -d ${D}/vidbinaries
    install -D ${S}/target/binaries/h264playtest.out ${D}/vidbinaries/
}

