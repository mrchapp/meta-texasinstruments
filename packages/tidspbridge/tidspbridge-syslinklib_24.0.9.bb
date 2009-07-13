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

FILES_${PN} = "${libdir}/libutils.so ${libdir}/libnotify.so ${libdir}/libprocmgr.so ${libdir}/libproc4430.so ${libdir}/libomap4430proc.so"
FILES_${PN} += "${libdir}/libipc.so ${libdir}/librcm.so"
FILES_${PN}-dev = "${libdir}/libutils.so.0 ${libdir}/libnotify.so.0 ${libdir}/libprocmgr.so.2 ${libdir}/libproc4430.so.2 ${libdir}/libipc.so.0 ${libdir}/librcm.so.0"

SRC_URI = "file://binaries.patch;patch=1"

inherit ccasefetch 

CCASE_SPEC = "%\
	element /vobs/WTSD_MultiCoreSW/MPU/Linux/... COMPONENT_ROOT%\
	element /vobs/WTSD_MultiCoreSW/MPU/Linux/...  L-SYSLINK-MPU_RLS_${PV}%\
	element * /main/LATEST%\
	"
CCASE_PATHFETCH = "/vobs/WTSD_MultiCoreSW/MPU/Linux"
CCASE_PATHCOMPONENT = "Linux"
CCASE_PATHCOMPONENTS = "3"

do_compile() {
	cp ${STAGING_LIBDIR}/libgcc.a ${S}/api/src/utils/
        cd ${S}/api/src
	oenote "In the mean time, directories need to be accessed in this order"

        oe_runmake  \
                PREFIX=${S} PROJROOT=${S} \
                ROOTFSDIR=${D} \
                CROSS=${AR%-*}- -f Makefile

	oe_runmake  \
                PREFIX=${S} PROJROOT=${S} \
                ROOTFSDIR=${D} \
                CROSS=${AR%-*}- -f Makefile install

	cd ${S}/api/src/utils/
	mv libutils.so libutils.so.0
        ln -s libutils.so.0 libutils.so

	cd ${S}/api/src/notify
	mv libnotify.so libnotify.so.0
        ln -s libnotify.so.0 libnotify.so

	#cd ${S}/api/src/procmgr

        cd ${S}/target/lib
	oenote "Files in target/lib: `ls -l`"
        ln -s libproc4430.so.2 libproc4430.so

        #cd ${S}/api/src/ipc

	#cd ${S}/api/src/rcm

	oenote "Processing syslink samples..."
	cd ${S}/samples
	export OLD_LDFLAGS=$LDFLAGS
        unset LDFLAGS
        cp ${STAGING_LIBDIR}/libpthread.so ${S}/target/lib
        cp ${STAGING_LIBDIR}/librt.so ${S}/target/lib
	oe_runmake \
		KRNLSRC=`cat ${STAGING_KERNEL_DIR}/kernel-source` \
                PREFIX=${S} TGTROOT=${S} \
                ARCH=arm \
                CROSS_COMPILE=${AR%-*}- -f Makefile

        export LDFLAGS=$OLD_LDFLAGS

}

do_stage() {
        oenote "Installing syslink libraries in ${STAGING_LIBDIR} "
	oe_libinstall -so -C ${S}/target/lib libproc4430 ${STAGING_LIBDIR}
        oe_libinstall -so -C ${S}/api/src/notify libnotify ${STAGING_LIBDIR}
        oe_libinstall -so -C ${S}/target/lib libprocmgr ${STAGING_LIBDIR}
        oe_libinstall -so -C ${S}/target/lib libipc ${STAGING_LIBDIR}
        oe_libinstall -so -C ${S}/api/src/utils libutils ${STAGING_LIBDIR}
        oe_libinstall -so -C ${S}/target/lib librcm ${STAGING_LIBDIR}

	oenote "Installing ducati scripts: `ls ${S}/scripts` "
	install -m 0755 ${S}/scripts/* ${STAGING_BINDIR}/dspbridge/samples
}

do_install() {
	oenote "Installing syslink libraries in ${D}/lib " 
	oe_libinstall -so -C ${S}/target/lib libproc4430 ${D}${libdir}
        oe_libinstall -so -C ${S}/api/src/notify libnotify ${D}${libdir}
        oe_libinstall -so -C ${S}/target/lib libprocmgr ${D}${libdir}
        oe_libinstall -so -C ${S}/target/lib libipc ${D}${libdir}
        oe_libinstall -so -C ${S}/api/src/utils libutils ${D}${libdir}
        oe_libinstall -so -C ${S}/target/lib librcm ${D}${libdir}

	cd ${S}/samples
	install -d ${STAGING_BINDIR}/dspbridge/samples
        install -D `find . -name notifyping.out -print` ${STAGING_BINDIR}/dspbridge/samples
	install -D `find . -name procmgrapp.out -print` ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/samples/proc_sample/krnl/procmgr_app.ko ${STAGING_LIBDIR}/modules
	install -D ${S}/samples/ipc/gatePeterson/usr/GatePetersonApp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/samples/ipc/sharedRegion/usr/SharedRegionApp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/samples/ipc/messageQ/usr/messageQApp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/samples/ipc/messageQ/usr/messageQApp1.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/samples/ipc/listMP/usr/listMPApp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/samples/ipc/heapBuf/usr/heapBufApp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/samples/ipc/nameServer/usr/NameServerApp.out ${STAGING_BINDIR}/dspbridge/samples
        install -D ${S}/samples/rcm/rcmtest.out ${STAGING_BINDIR}/dspbridge/samples

}
