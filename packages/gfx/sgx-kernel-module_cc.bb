SECTION = "kernel/modules"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies SGX Power VR module."
LICENSE = "GPL"
PR = "r2"
COMPATIBLE_MACHINE = "omap-4430sdp"

DEPENDS = " virtual/kernel "

inherit module ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN} ${PN}-dbg"
FILES_${PN} = "${bindir}/* ${libdir}/* ${sysconfdir}/* /lib/modules/*"
FILES_${PN}-dbg = "${bindir}/.debug/* ${libdir}/.debug/* ${sysconfdir}/* /lib/modules/.debug/*"

SRC_URI = " file://regtool"

PV = "0.0+cc+${SRCREV}"

CCASE_SPEC = "%\
	element * COMPONENT_ROOT%\
	element /vobs/wtbu/OMAPSW_GFX/... ${SRCREV}%\
"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_GFX/IMAGINATION/GFX/GFX_Linux_DDK"
CCASE_PATHCOMPONENT = "GFX_Linux_DDK"
CCASE_PATHCOMPONENTS = "5"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	cd ${S}/src/eurasia_km/eurasiacon/build/linux/omap4430_linux/kbuild
	oe_runmake GFX_DDK_DIR=${S} EURASIAROOT=${S}/src/eurasia_km KERNELDIR=${STAGING_KERNEL_DIR} V=1 ARCH=arm \
		DISCIMAGE=${STAGING_DIR_TARGET} X11ROOT=${prefix} CROSS=${AR%-*}-
}

do_install() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	cd ${S}/src/eurasia_km/eurasiacon/build/linux/omap4430_linux/kbuild
	oe_runmake GFX_DDK_DIR=${S} EURASIAROOT=${S}/src/eurasia_km KERNELDIR=${STAGING_KERNEL_DIR} V=1 ARCH=arm \
		DISCIMAGE=${D} X11ROOT=${prefix} CROSS=${AR%-*}- \
		install
    install -d ${D}${bindir}
    install -m 0777 ${WORKDIR}/regtool ${D}${bindir}

}

