SECTION="libs"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Syslink libraries."
LICENSE = "GPL"
PR = "r0"
DEPENDS = " \
        linux-tiomap \
	tidspbridge-tiler \
        "

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"
# We need to override this and make sure it's only -j1
PARALLEL_MAKE = "-j1"

FILES_${PN} = "${libdir}/lib*.so \
		${base_libdir}/lib*.so \
		/dspbridge \
		"
FILES_${PN}-dev = "${libdir}/lib*.so* \
		${base_libdir}/lib*.so* \
		${base_libdir}/lib*.a"

FILES_${PN}-dbg = " \
		${libdir}/.debug \
		${base_libdir}/.debug \
		/dspbridge/.debug \
		"

SRC_URI = "\
    git://dev.omapzoom.org/pub/scm/tisyslink/userspace-syslink.git;protocol=git\
    file://install_ducati_syslink.patch;patch=1 \
    file://install_tesla.patch;patch=1 \
    file://install_syslink.patch;patch=1 \
    "

S = "${WORKDIR}/git"

PV = "24.0+git+${SRCREV}"


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
	oe_libinstall -so -C ${STAGING_LIBDIR} libdmmdrv ${S}/target/lib
	oe_libinstall -so -C ${STAGING_DIR_TARGET}${layout_base_libdir} libgcc_s ${S}/target/lib

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


	oenote "Processing syslink daemons..."

	cd ${S}/daemons
	export KRNLSRC=`cat ${STAGING_KERNEL_DIR}/kernel-source`
        export USE_SYSMGR=`cat ${KRNLSRC}/.config |grep CONFIG_SYSLINK_USE_SYSMGR`
        if [ ${USE_SYSMGR} != "CONFIG_SYSLINK_USE_SYSMGR=y" ]
        then
		oenote "Deselecting SYS Manager for daemons: ${USE_SYSMGR}"
		sed -i 's/^CFLAGS += -DSYSLINK_USE_SYSMGR/#CFLAGS += -DSYSLINK_USE_SYSMGR/g' tiler/Makefile
                sed -i 's/^SH_LIBS        += sysmgr sysmemmgr/#SH_LIBS        += sysmgr sysmemmgr/g' tiler/Makefile
        else
		oenote "Selecting SYS Manager for daemons: ${USE_SYSMGR}"
		sed -i 's/^#CFLAGS += -DSYSLINK_USE_SYSMGR/CFLAGS += -DSYSLINK_USE_SYSMGR/g' tiler/Makefile
                sed -i 's/^#SH_LIBS        += sysmgr sysmemmgr/SH_LIBS        += sysmgr sysmemmgr/g' tiler/Makefile
        fi

	oe_runmake  \
                PREFIX=${S} TGTROOT=${S} \
                CROSS_COMPILE=${AR%-*}- -f Makefile clean

        oe_runmake  \
                PREFIX=${S} TGTROOT=${S} \
                CROSS_COMPILE=${AR%-*}- -f Makefile

        oe_runmake  \
                PREFIX=${S} TGTROOT=${S} \
                CROSS_COMPILE=${AR%-*}- -f Makefile install


	oenote "Processing syslink samples..."
	export OLD_LDFLAGS=$LDFLAGS
        unset LDFLAGS

	cd ${S}/samples
	if [ ${USE_SYSMGR} != "CONFIG_SYSLINK_USE_SYSMGR=y" ]
	then
		oenote "Deselecting SYS Manager for samples: ${USE_SYSMGR}"
		sed -i 's/^CDEFS          += SYSLINK_USE_SYSMGR/#CDEFS          += SYSLINK_USE_SYSMGR/g' rcm/single_test/Makefile
		sed -i 's/^SH_LIBS        += sysmgr sysmemmgr/#SH_LIBS        += sysmgr sysmemmgr/g' rcm/single_test/Makefile
		sed -i 's/^CDEFS          += SYSLINK_USE_SYSMGR/#CDEFS          += SYSLINK_USE_SYSMGR/g' rcm/multi_test/Makefile
                sed -i 's/^SH_LIBS        += sysmgr sysmemmgr/#SH_LIBS        += sysmgr sysmemmgr/g' rcm/multi_test/Makefile
                sed -i 's/^CFLAGS += -DSYSLINK_USE_SYSMGR/#CFLAGS += -DSYSLINK_USE_SYSMGR/g' ipc/messageQ/usr/Makefile
		sed -i 's/^LIBS += -lsysmgr -lsysmemmgr/#LIBS += -lsysmgr -lsysmemmgr/g' ipc/messageQ/usr/Makefile
	else
		oenote "Selecting SYS Manager for samples: ${USE_SYSMGR}"
                sed -i 's/^#CDEFS          += SYSLINK_USE_SYSMGR/CDEFS          += SYSLINK_USE_SYSMGR/g' rcm/single_test/Makefile
                sed -i 's/^#SH_LIBS        += sysmgr sysmemmgr/SH_LIBS        += sysmgr sysmemmgr/g' rcm/single_test/Makefile
                sed -i 's/^#CDEFS          += SYSLINK_USE_SYSMGR/CDEFS          += SYSLINK_USE_SYSMGR/g' rcm/multi_test/Makefile
                sed -i 's/^#SH_LIBS        += sysmgr sysmemmgr/SH_LIBS        += sysmgr sysmemmgr/g' rcm/multi_test/Makefile
                sed -i 's/^#CFLAGS += -DSYSLINK_USE_SYSMGR/CFLAGS += -DSYSLINK_USE_SYSMGR/g' ipc/messageQ/usr/Makefile
                sed -i 's/^#LIBS += -lsysmgr -lsysmemmgr/LIBS += -lsysmgr -lsysmemmgr/g' ipc/messageQ/usr/Makefile
	fi


	if [ ${USE-LOADER_tidspbridge-syslinklib} == "true" ]
        then
		oenote "Enabling LOADER for samples: USE-LOADER=${USE-LOADER_tidspbridge-syslinklib}"
		sed -i 's/^#CFLAGS += -DSYSLINK_USE_LOADER/CFLAGS += -DSYSLINK_USE_LOADER/' ipc/messageQ/usr/Makefile
		sed -i 's/^#CDEFS          += SYSLINK_USE_LOADER/CDEFS          += SYSLINK_USE_LOADER/' rcm/multi_test/Makefile
		sed -i 's/^#CDEFS          += SYSLINK_USE_LOADER/CDEFS          += SYSLINK_USE_LOADER/' rcm/single_test/Makefile
	else
		oenote "Disabling LOADER for samples: USE-LOADER=${USE-LOADER_tidspbridge-syslinklib}"
		sed -i 's/^CFLAGS += -DSYSLINK_USE_LOADER/#CFLAGS += -DSYSLINK_USE_LOADER/' ipc/messageQ/usr/Makefile
                sed -i 's/^CDEFS          += SYSLINK_USE_LOADER/#CDEFS          += SYSLINK_USE_LOADER/' rcm/multi_test/Makefile
                sed -i 's/^CDEFS          += SYSLINK_USE_LOADER/#CDEFS          += SYSLINK_USE_LOADER/' rcm/single_test/Makefile
	fi

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
	oe_libinstall -so -C ${S}/target/lib libgcc_s ${D}${base_libdir}

	oenote "Installing ducati scripts: `ls ${S}/scripts` "
	install -d ${D}/dspbridge
	install -D ${S}/target/notify/notifyping.out ${D}/dspbridge/
	install -D ${S}/target/procmgrapps/procmgrapp.out ${D}/dspbridge/
	install -D ${S}/target/ipc/GatePetersonApp.out ${D}/dspbridge/
	install -D ${S}/target/ipc/SharedRegionApp.out ${D}/dspbridge/
	install -D ${S}/target/ipc/messageQApp.out ${D}/dspbridge/
	install -D ${S}/samples/ipc/messageQ/usr/messageQApp1.out ${D}/dspbridge/
	install -D ${S}/target/ipc/listMPApp.out ${D}/dspbridge/
	install -D ${S}/target/ipc/heapBufApp.out ${D}/dspbridge/
	install -D ${S}/target/ipc/NameServerApp.out ${D}/dspbridge/
	install -D ${S}/target/target/rcm_multitest.out ${D}/dspbridge/
	install -D ${S}/target/target/rcm_singletest.out ${D}/dspbridge/
	install -D ${S}/target/procmgrapps/ducati_load.out ${D}/dspbridge/
	install -D ${S}/target/target/tiler_daemon.out ${D}/dspbridge/

	oenote "Installing modules..."
	install -D ${S}/target/binaries/procmgr_app.ko ${D}/dspbridge/

	oenote "Installing install scripts..."
	install -D ${S}/scripts/install_syslink ${D}/dspbridge/
	install -D ${S}/scripts/install_tesla_bridge ${D}/dspbridge/
	install -D ${S}/scripts/install_ducati_syslink ${D}/dspbridge/
	install -D ${S}/scripts/uninstall* ${D}/dspbridge/

}

