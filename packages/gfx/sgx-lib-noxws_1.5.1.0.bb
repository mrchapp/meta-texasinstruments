SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies SGX Power VR OpenGL libs (no X support)"
LICENSE = "GPL"
PR = "r1"
COMPATIBLE_MACHINE = "zoom(2|3)|omap-3(4|6)30(l|s)dp|beagleboard"
RDEPENDS = sgx-kernel-module
DEPENDS = virtual/kernel

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"
FILES_${PN} = "/usr/local/bin/* ${libdir} ${sysconfdir}/init.d/rc.pvr ${sysconfdir}/powervr.ini"

SRC_URI = "file://noxsupport.patch;patch=1"

CCASE_SPEC = "%\
	element * COMPONENT_ROOT%\
	element /vobs/wtbu/OMAPSW_GFX/... GFX-LINUX-SGX-DDK-23X-INC3_RLS_${PV}%\
	element * /main/LATEST%\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_GFX/IMAGINATION/GFX/GFX_Linux_DDK"
CCASE_PATHCOMPONENT = "GFX_Linux_DDK"
CCASE_PATHCOMPONENTS = "5"

KERNEL_VERSION=`cat ${STAGING_KERNEL_DIR}/kernel-abiversion`

# hack.  Carlos knows to fix it in future revisions.
do_chmod() {
	chmod -R +w ${S}/gfx_linux_ddk/eurasia
}

do_compile() {
	cd ${S}/gfx_linux_ddk/eurasia/eurasiacon/build/linux/omap3630_linux
	oe_runmake EURASIAROOT=${S}/gfx_linux_ddk/eurasia KERNELDIR=${STAGING_KERNEL_DIR} BUILD=release \
		DISCIMAGE=${STAGING_DIR_TARGET} X11ROOT=${prefix} V=1 SUPPORT_XWS=0
}

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}
	cd ${S}/gfx_linux_ddk/eurasia/eurasiacon/build/linux/omap3630_linux
	oe_runmake EURASIAROOT=${S}/gfx_linux_ddk/eurasia KERNELDIR=${STAGING_KERNEL_DIR} BUILD=release \
		DISCIMAGE=${D} X11ROOT=${prefix} CROSS=${AR%-*}- \
		SUPPORT_XWS=0 install

	cat > ${D}/etc/powervr.ini <<EOF
[default]
WindowSystem=libpvrPVR2D_FRONTWSEGL.so
EOF

}

addtask chmod after do_unpack_ccase before do_patch