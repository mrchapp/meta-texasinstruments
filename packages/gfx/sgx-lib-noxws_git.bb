SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies SGX Power VR OpenGL libs (no X support)"
LICENSE = "GPL"
PR = "r0"
COMPATIBLE_MACHINE = "zoom(2|3)|omap-3(4|6)30(l|s)dp|beagleboard"
RDEPENDS = sgx-kernel-module
DEPENDS = virtual/kernel

GFORGE_USER ?= ${USER} 

PV = "23.i3+git+${SRCREV}"
SRC_GFORGE = "ssh://${GFORGE_USER}@gforge01.dal.design.ti.com/gitroot/gfx_l23_ddk"
S = "${WORKDIR}/git"

inherit gforgefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"
FILES_${PN} = "/usr/local/bin/* ${libdir} ${sysconfdir}/init.d/rc.pvr ${sysconfdir}/powervr.ini"

SRC_URI += "file://noxsupport.patch;patch=1"

GFORGE_PATHFETCH="\
 TI_Linux_OMAP_SGX_DDK.txt \
 docs \
 glsl_compilers \
 omapsw_gfx_sgx_linuxddk_testreport_* \
 build_DDK.sh \
 eurasia \
 freedesktop \
 omapsw_gfx_linuxddk_relnotes_* \
 "

KERNEL_VERSION=`cat ${STAGING_KERNEL_DIR}/kernel-abiversion`

do_compile() {
	cd ${S}/eurasia/eurasiacon/build/linux/omap3630_linux
	oe_runmake EURASIAROOT=${S}/eurasia KERNELDIR=${STAGING_KERNEL_DIR} BUILD=release \
		DISCIMAGE=${STAGING_DIR_TARGET} X11ROOT=${prefix} V=1 SUPPORT_XWS=0
}

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}
	cd ${S}/eurasia/eurasiacon/build/linux/omap3630_linux
	oe_runmake EURASIAROOT=${S}/eurasia KERNELDIR=${STAGING_KERNEL_DIR} BUILD=release \
		DISCIMAGE=${D} X11ROOT=${prefix} CROSS=${AR%-*}- \
		SUPPORT_XWS=0 install

	cat > ${D}/etc/powervr.ini <<EOF
[default]
WindowSystem=libpvrPVR2D_FRONTWSEGL.so
EOF

}
