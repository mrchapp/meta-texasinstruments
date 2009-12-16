PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Linux Video OMX Test S/W"
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
LICENSE = "LGPL"
PR = "r0"

DEPENDS = " \
   titiler-d2cmap \
   titiler-memmgr \
   tisyslink-lib \
   "
inherit pkgconfig ccasefetch

PV = "0.0+cc+${SRCREV}"

SRC_URI = "\
    file://include.patch;patch=1 \
    file://build.patch;patch=1 \
    file://Makefile.patch;patch=1 \
"

CCASE_SPEC = "%\
   element /vobs/WTSD_DucatiMMSW/...   ${SRCREV}%\
   element * /main/LATEST%\
"

# Note: WTSD_DucatiMMSW is used in the XDC package name, so it must be put
# in the ${S} folder
CCASE_PATHFETCH = " \
         /vobs/WTSD_DucatiMMSW/framework/domx_linux_sample \
         "
CCASE_PATHCOMPONENT = "vobs"
CCASE_PATHCOMPONENTS = "0"

FILES_${PN} += "/vidbinaries/input /vidbinaries/output /vidbinaries/*.out"
FILES_${PN}-dev += ""
FILES_${PN}-dbg += "/vidbinaries/.debug/*.out"

do_compile() {

    install -d ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target
    install -d ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target/lib


    oenote "Compilation of DOMX core:"
    cd ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/src

    oenote "Cleaning DOMX src:"
    oe_runmake \
        PROJROOT=${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/src \
        TILER_INC_PATH=${STAGING_INCDIR} \
        BRIDGEROOT=${S} \
                clean

    oenote "Compiling DOMX src:"
    oe_runmake \
        PROJROOT=${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/src \
        TILER_INC_PATH=${STAGING_INCDIR} \
        BRIDGEROOT=${S}

    oenote "Installing DOMX src:"
    oe_runmake \
        PROJROOT=${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/src \
        TILER_INC_PATH=${STAGING_INCDIR} \
        BRIDGEROOT=${S} \
                install

    oenote "Copying DOMX dynamic libraries:"
        cp ${STAGING_LIBDIR}/libutils.so ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target/lib 
        cp ${STAGING_LIBDIR}/libprocmgr.so ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target/lib 
        cp ${STAGING_LIBDIR}/libipc.so ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target/lib 
        cp ${STAGING_LIBDIR}/librcm.so ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target/lib 
        cp ${STAGING_LIBDIR}/libnotify.so ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target/lib 
        cp ${STAGING_LIBDIR}/libsysmgr.so ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target/lib 
        cp ${STAGING_LIBDIR}/libsysmemmgr.so ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target/lib 
        cp ${STAGING_LIBDIR}/libmemmgr.so ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target/lib 
        cp ${STAGING_LIBDIR}/libd2cmap.so ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target/lib 


    oenote "Compilation of DOMX samples:"
    cd ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/test

    oenote "Cleaning DOMX sample test:"
    oe_runmake \
        PROJROOT=${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/test \
        TILER_INC_PATH=${STAGING_INCDIR} \
        BRIDGEROOT=${S} \
                clean

    oenote "Compiling DOMX sample test:"
    oe_runmake \
        PROJROOT=${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/test \
        TILER_INC_PATH=${STAGING_INCDIR} \
        BRIDGEROOT=${S}
    oe_runmake \
        PROJROOT=${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/test \
        TILER_INC_PATH=${STAGING_INCDIR} \
        BRIDGEROOT=${S} \
                install
}

do_stage() {
    install -d ${STAGING_DIR}/video
    (cd ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample; cp -r * ${STAGING_DIR}/video/)
}

do_install() {
    oenote "Installing Ducati test application:"
    install -d ${D}/vidbinaries
    install -d ${D}/vidbinaries/input
    install -d ${D}/vidbinaries/output
    install -D ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target/binaries/h264enctest.out ${D}/vidbinaries/
    install -D ${S}/WTSD_DucatiMMSW/framework/domx_linux_sample/target/binaries/h264dectest.out ${D}/vidbinaries/
}

