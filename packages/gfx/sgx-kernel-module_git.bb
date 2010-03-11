SECTION = "kernel/modules"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies SGX Power VR module."
LICENSE = "GPL"
PR = "r0"
COMPATIBLE_MACHINE = "zoom(2|3)|omap-3(4|6)30(l|s)dp|beagleboard"

GFORGE_USER ?= ${USER} 

PV = "23.i3+git+${SRCREV}"
SRC_GFORGE = "ssh://${GFORGE_USER}@gforge01.dal.design.ti.com/gitroot/gfx_l23_ddk"
S = "${WORKDIR}/git"

inherit module gforgefetch

SRC_URI += " \
file://sgx-gcc-4.3-error.patch;patch=1 \
"
GFORGE_PATHFETCH="\
 eurasia_km \
 "

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	cd ${S}/eurasia_km/eurasiacon/build/linux/omap3630_linux/kbuild
	oe_runmake EURASIAROOT=${S}/eurasia_km KERNELDIR=${STAGING_KERNEL_DIR} BUILD=release \
		DISCIMAGE=${STAGING_DIR_TARGET} X11ROOT=${prefix} CROSS=${AR%-*}-
}

do_install() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	cd ${S}/eurasia_km/eurasiacon/build/linux/omap3630_linux/kbuild
	oe_runmake EURASIAROOT=${S}/eurasia_km KERNELDIR=${STAGING_KERNEL_DIR} BUILD=release \
		DISCIMAGE=${D} X11ROOT=${prefix} CROSS=${AR%-*}- \
		install
}
