SECTION="drivers"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Tiler Driver."
LICENSE = "GPL"
PR = "r0"
DEPENDS = " \
        linux-tiomap \
        "
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"

#FILES_${PN} = "${libdir}/libutils.so"
#FILES_${PN} += "${libdir}/libipc.so"
#FILES_${PN}-dev = "${libdir}/libutils.so.0"

#SRC_URI = "file://binaries.patch;patch=1"

inherit ccasefetch 

CCASE_SPEC = "%\
	element /vobs/WTSD_MultiCoreSW/Tiler/... COMPONENT_ROOT%\
	element /vobs/WTSD_MultiCoreSW/Tiler/... L-MMKRN-MPU_RLS_${PV}%\
	element * /main/LATEST%\
	"
CCASE_PATHFETCH = "/vobs/WTSD_MultiCoreSW/Tiler"
CCASE_PATHCOMPONENT = "Tiler"
CCASE_PATHCOMPONENTS = "2"

do_compile() {

	export OLD_LDFLAGS=$LDFLAGS
        unset LDFLAGS
	cd ${S}/krnl
	oe_runmake \
		KRNLSRC=`cat ${STAGING_KERNEL_DIR}/kernel-source` \
                PREFIX=${S} TGTROOT=${S} \
                ARCH=arm \
                CROSS_COMPILE=${AR%-*}- -f Makefile

	export LDFLAGS=$OLD_LDFLAGS
}

do_stage() {
	oenote "Installing modules..."
	if [ ! -d ${STAGING_LIBDIR}/modules ]
	then
		install -d ${STAGING_LIBDIR}/modules
	fi
	install -D krnl/tiler.ko ${STAGING_LIBDIR}/modules
}
