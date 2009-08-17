SECTION="libs"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Syslink libraries."
LICENSE = "GPL"
PR = "r0"
DEPENDS = " \
        linux-tiomap \
        "
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"

#FILES_${PN} = "${libdir}/libutils.so ${libdir}/libnotify.so"
#FILES_${PN} += "${libdir}/libprocmgr.so ${libdir}/libproc4430.so"
#FILES_${PN} += "${libdir}/libomap4430proc.so"
#FILES_${PN} += "${libdir}/libipc.so ${libdir}/librcm.so ${libdir}/libdmmdrv.so"
#FILES_${PN} += "${libdir}/libsysmemmgr.so ${libdir}/libsysmgr.so"
#FILES_${PN} += "${libdir}/librt.so.1"
#FILES_${PN} += "${base_libdir}/libpthread.so ${base_libdir}/librt.so"
#FILES_${PN}-dev = "${libdir}/libutils.so.0 ${libdir}/libnotify.so.0"
#FILES_${PN}-dev += "${libdir}/libprocmgr.so.2 ${libdir}/libproc4430.so.2"
#FILES_${PN}-dev += "${libdir}/libipc.so.0 ${libdir}/librcm.so.0"
#FILES_${PN}-dev += "${libdir}/libdmmdrv.so.0 ${libdir}/libsysmemmgr.so.0"
#FILES_${PN}-dev += "${libdir}/libsysmgr.so.0"
#FILES_${PN}-dev += "${base_libdir}/libgcc.a"
FILES_${PN} = "${libdir}/lib*.so \
		${base_libdir}/lib*.so"
FILES_${PN}-dev = "${libdir}/lib*.so* \
		${base_libdir}/lib*.so* \
		${base_libdir}/lib*.a"

SRC_URI = "file://binaries.patch;patch=1 \
	   file://install_tesla.patch;patch=1"

inherit ccasefetch 

CCASE_SPEC = "%\
	element /vobs/WTSD_MultiCoreSW/MPU/Linux/...  L-SYSLINK-MPU_RLS_${PV}%\
	element /vobs/WTSD_MultiCoreSW/MPU/Linux/api/src/tilermgr/... L-MMUSR-MPU_RLS_${PV}%\
	element /vobs/WTSD_MultiCoreSW/MPU/Linux/... COMPONENT_ROOT%\
	element * /main/LATEST%\
	"
CCASE_PATHFETCH = "/vobs/WTSD_MultiCoreSW/MPU/Linux"
CCASE_PATHCOMPONENT = "Linux"
CCASE_PATHCOMPONENTS = "3"

do_compile() {
	install -d ${S}/target/lib
	cd ${S}/target/lib
	cp ${STAGING_LIBDIR}/libgcc.a .
        cp -prf ${EXTERNAL_TOOLCHAIN}/arm-none-linux-gnueabi/libc/lib/libpthread-2.8.so .
        cp -prf ${EXTERNAL_TOOLCHAIN}/arm-none-linux-gnueabi/libc/lib/libpthread.so.0 .
        cp -prf ${EXTERNAL_TOOLCHAIN}/arm-none-linux-gnueabi/libc/lib/librt.so.1 .
        cp -prf ${EXTERNAL_TOOLCHAIN}/arm-none-linux-gnueabi/libc/lib/librt-2.8.so .
        ln -s libpthread.so.0 libpthread.so
        ln -s librt.so.1 librt.so

        cd ${S}/api/src

	oe_runmake  \
                PREFIX=${S} TGTROOT=${S} \
                CROSS=${AR%-*}- -f Makefile clean

        oe_runmake  \
                PREFIX=${S} TGTROOT=${S} \
                CROSS=${AR%-*}- -f Makefile

	oe_runmake  \
                PREFIX=${S} TGTROOT=${S} \
                CROSS=${AR%-*}- -f Makefile install


	cd ${S}/api/src/tilermgr

	oe_runmake  \
                PREFIX=${S} TGTROOT=${S} \
                CROSS=${AR%-*}- -f Makefile clean

	oe_runmake  \
                PREFIX=${S} TGTROOT=${S} \
                CROSS_COMPILE=${AR%-*}- -f Makefile

	oe_runmake  \
                PREFIX=${S} TGTROOT=${S} \
                CROSS=${AR%-*}- -f Makefile install


	oenote "Processing syslink samples..."
	export OLD_LDFLAGS=$LDFLAGS
        unset LDFLAGS

	cd ${S}/samples

	oe_runmake \
                KRNLSRC=`cat ${STAGING_KERNEL_DIR}/kernel-source` \
                PREFIX=${S} TGTROOT=${S} \
                ARCH=arm \
                CROSS_COMPILE=${AR%-*}- -f Makefile clean

	oe_runmake \
		KRNLSRC=`cat ${STAGING_KERNEL_DIR}/kernel-source` \
                PREFIX=${S} TGTROOT=${S} \
                ARCH=arm \
                CROSS_COMPILE=${AR%-*}- -f Makefile

	oe_runmake \
                KRNLSRC=`cat ${STAGING_KERNEL_DIR}/kernel-source` \
                PREFIX=${S} TGTROOT=${S} \
                ARCH=arm \
                CROSS_COMPILE=${AR%-*}- -f Makefile install

        export LDFLAGS=$OLD_LDFLAGS

}

do_stage() {
        oenote "Installing syslink libraries in ${STAGING_LIBDIR} "
	oe_libinstall -so -C ${S}/target/lib libproc4430 ${STAGING_LIBDIR}
        oe_libinstall -so -C ${S}/target/lib libnotify ${STAGING_LIBDIR}
        oe_libinstall -so -C ${S}/target/lib libprocmgr ${STAGING_LIBDIR}
        oe_libinstall -so -C ${S}/target/lib libipc ${STAGING_LIBDIR}
        oe_libinstall -so -C ${S}/target/lib libutils ${STAGING_LIBDIR}
        oe_libinstall -so -C ${S}/target/lib librcm ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/target/lib libsysmemmgr ${STAGING_LIBDIR}
	oe_libinstall -so -C ${S}/target/lib libsysmgr ${STAGING_LIBDIR}
	#oe_libinstall -so -C ${S}/target/lib librt ${STAGING_LIBDIR}
	#oe_libinstall -so -C ${S}/target/lib libpthread ${STAGING_LIBDIR}

	oenote "Installing tiler libraries in ${STAGING_LIBDIR} "
	oe_libinstall -so -C ${S}/api/src/tilermgr libdmmdrv ${STAGING_LIBDIR}

	oenote "Installing ducati scripts: `ls ${S}/scripts` "
	if [ ! -d ${STAGING_BINDIR}/dspbridge/samples ]
        then
                install -d ${STAGING_BINDIR}/dspbridge/samples
        fi

	install -m 0755 ${S}/scripts/* ${STAGING_BINDIR}/dspbridge/samples

	install -D ${S}/target/notify/notifyping.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/target/procmgrapps/procmgrapp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/target/ipc/GatePetersonApp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/target/ipc/SharedRegionApp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/target/ipc/messageQApp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/samples/ipc/messageQ/usr/messageQApp1.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/target/ipc/listMPApp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/target/ipc/heapBufApp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/target/ipc/NameServerApp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/target/target/rcm_multitest.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/target/target/rcm_singletest.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/target/procmgrapps/ducati_load.out ${STAGING_BINDIR}/dspbridge/samples

        oenote "Installing modules..."
        if [ ! -d ${STAGING_LIBDIR}/modules ]
        then
                install -d ${STAGING_LIBDIR}/modules
        fi
        install -D ${S}/target/binaries/procmgr_app.ko ${STAGING_LIBDIR}/modules

}

do_install() {
	oenote "Installing syslink libraries in ${D}/lib " 
	oe_libinstall -so -C ${S}/target/lib libproc4430 ${D}${libdir}
        oe_libinstall -so -C ${S}/target/lib libnotify ${D}${libdir}
        oe_libinstall -so -C ${S}/target/lib libprocmgr ${D}${libdir}
        oe_libinstall -so -C ${S}/target/lib libipc ${D}${libdir}
        oe_libinstall -so -C ${S}/target/lib libutils ${D}${libdir}
        oe_libinstall -so -C ${S}/target/lib librcm ${D}${libdir}
	oe_libinstall -so -C ${S}/target/lib libsysmemmgr ${D}${libdir} 
        oe_libinstall -so -C ${S}/target/lib libsysmgr ${D}${libdir} 
	oe_libinstall -so -C ${S}/target/lib librt ${D}${base_libdir}
	oe_libinstall -a -C ${S}/target/lib libgcc ${D}${base_libdir}
	oe_libinstall -so -C ${S}/target/lib libpthread ${D}${base_libdir}

	oenote "Installing tiler libraries in ${D}/lib "
        oe_libinstall -so -C ${S}/api/src/tilermgr libdmmdrv ${D}${libdir}
}
