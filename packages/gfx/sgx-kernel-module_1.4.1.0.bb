SECTION = "kernel/modules"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies SGX Power VR module."
LICENSE = "GPL"
PR = "r0"
COMPATIBLE_MACHINE = "zoom(2|3)|omap-3(4|6)30(l|s)dp|beagleboard"

inherit module ccasefetch

SRC_URI = "file://sgx-gcc-4.3-error.patch;patch=1"

CCASE_SPEC = "%\
	element * COMPONENT_ROOT%\
	element /vobs/wtbu/OMAPSW_GFX/... GFX-LINUX-SGX-DDK-23X-INC3_RLS_${PV}%\
	element * /main/LATEST%\
	"
CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_GFX/IMAGINATION/GFX/GFX_Linux_DDK/src/eurasia_km"
CCASE_PATHCOMPONENT = "eurasia_km"
CCASE_PATHCOMPONENTS = "7"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	cd ${S}/eurasiacon/build/linux/omap3630_linux/kbuild
	oe_runmake EURASIAROOT=${S} KERNELDIR=${STAGING_KERNEL_DIR} \
		DISCIMAGE=${STAGING_DIR_TARGET} X11ROOT=${prefix} CROSS=${AR%-*}-
}

do_install() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	cd ${S}/eurasiacon/build/linux/omap3630_linux/kbuild
	oe_runmake EURASIAROOT=${S} KERNELDIR=${STAGING_KERNEL_DIR} \
		DISCIMAGE=${D} X11ROOT=${prefix} CROSS=${AR%-*}- \
		install
}
