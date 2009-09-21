PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Syslink libraries."
LICENSE = "GPL"
PR = "r0"
DEPENDS = " \
        linux-tiomap \
        "
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}-dev ${PN}-dbg ${PN}"

FILES_${PN}-dev = "/lib/libomap4430proc.so /lib/libnotify.so /lib/libprocmgr.so /lib/libipc.so /lib/libutils.so /lib/librcm.so"
FILES_${PN}-dbg = "/lib/.debug"
FILES_${PN} = "/lib"

SRC_URI = " \
       file://mkproc-sample.patch;patch=1 \
       "


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

        cd ${S}/api/src
	oenote "In the mean time, directories need to be accessed in this order"
	cd ${S}/api/src/utils
	cp ${STAGING_LIBDIR}/libgcc.a .

        oe_runmake  \
                PREFIX=${S} PROJROOT=${S} \
                ROOTFSDIR=${D} \
                CROSS=${AR%-*}- -f Makefile

	oe_runmake  \
                PREFIX=${S} PROJROOT=${S} \
                ROOTFSDIR=${D} \
                CROSS=${AR%-*}- -f Makefile install

	cd ${S}/api/src/notify
        oe_runmake  \
                PREFIX=${S} PROJROOT=${S} \
                ROOTFSDIR=${D} \
                CROSS=${AR%-*}- -f Makefile

	oe_runmake  \
                PREFIX=${S} PROJROOT=${S} \
                ROOTFSDIR=${D} \
                CROSS=${AR%-*}- -f Makefile install


	cd ${S}/api/src/procmgr
        oe_runmake  \
                PREFIX=${S} PROJROOT=${S} \
                ROOTFSDIR=${D} \
                CROSS=${AR%-*}- -f Makefile

	oe_runmake  \
                PREFIX=${S} PROJROOT=${S} \
                ROOTFSDIR=${D} \
                CROSS=${AR%-*}- -f Makefile install


        cd ${S}/api/src/ipc
        oe_runmake  \
                PREFIX=${S} PROJROOT=${S} \
                ROOTFSDIR=${D} \
                CROSS=${AR%-*}- -f Makefile

	oe_runmake  \
                PREFIX=${S} PROJROOT=${S} \
                ROOTFSDIR=${D} \
                CROSS=${AR%-*}- -f Makefile install

	cd ${S}/api/src/rcm
        oe_runmake  \
                PREFIX=${S} PROJROOT=${S} \
                ROOTFSDIR=${D} \
                CROSS=${AR%-*}- -f Makefile

	oe_runmake  \
                PREFIX=${S} PROJROOT=${S} \
                ROOTFSDIR=${D} \
                CROSS=${AR%-*}- -f Makefile install

	cd ${S}/samples
	oenote "Kernel staging: ${STAGING_KERNEL_DIR}"
	oe_runmake KRNLSRC=${STAGING_KERNEL_DIR} \
                PREFIX=${S} PROJROOT=${S}/samples \
                ROOTFSDIR=${D} ARCH=arm \
                CROSS=${AR%-*}- -f Makefile

}

do_stage() {
        oenote "Installing syslink libraries in ${STAGING_LIBDIR} "
	install -d ${STAGING_LIBDIR}/dspbridge/syslink
        cp -d ${S}/target/lib/* ${STAGING_LIBDIR}/dspbridge/syslink

	oenote "Installing ducati scripts: `ls ${S}/scripts` "
	install -m 0755 ${S}/scripts/* ${STAGING_BINDIR}/dspbridge/samples
}

do_install() {
	install -d ${D}/lib
	oenote "Installing syslink libraries in ${D}/lib " 
	cp -d ${S}/target/lib/* ${D}/lib

	cd ${S}/samples
	install -d ${STAGING_BINDIR}/dspbridge/samples
	install -D `find . -name notifyping.out -print` ${STAGING_BINDIR}/dspbridge/samples
	install -D `find . -name procmgrapp.out -print` ${STAGING_BINDIR}/dspbridge/samples

}
