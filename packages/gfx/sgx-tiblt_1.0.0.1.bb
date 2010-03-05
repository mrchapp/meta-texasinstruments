SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "GFX TICPUBLT 2D acceleration library"
LICENSE = "GPL"
PR = "r0"
COMPATIBLE_MACHINE = "zoom(2|3)|omap-3(4|6)30(l|s)dp|beagleboard"

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN} ${PN}-dbg"
FILES_${PN} = "${bindir}/* ${libdir}/*"
FILES_${PN}-dbg = "${bindir}/.debug/* ${libdir}/.debug/*"

CCASE_SPEC = "%\
    element /vobs/OMAPSW_TIBLT_2DGFX/... OMAP_LINUX_TICPUBLT_BIN_RLS_${PV}%\
"

CCASE_PATHFETCH = "/vobs/OMAPSW_TIBLT_2DGFX/TIBLT/release/Linux"
CCASE_PATHCOMPONENT = "Linux"
CCASE_PATHCOMPONENTS = "4"

do_compile() {
    cd ${S}/lib/
    ln -s libTICPUBLT.so.${PV} libTICPUBLT.so
    ln -s libTICPUBLT_TI.so.${PV} libTICPUBLT_TI.so
   
    cd ${S}/apps/SolidFill
    oe_runmake clean
    oe_runmake
}

do_stage() {
    install -d ${STAGING_INCDIR}/${PN}/
	install -m 644 ${S}/inc/TIBLT.h ${STAGING_INCDIR}/${PN}/
}

do_install() {
	install -d ${D}${bindir}
	install -m 755 ${S}/apps/SolidFill/SolidFill ${D}${bindir}
	install -m 755 ${S}/apps/SolidFill/input.rgb ${D}${bindir}
	install -d ${D}${libdir}
	oe_libinstall -so -C ${S}/lib libTICPUBLT ${D}${libdir}
	oe_libinstall -so -C ${S}/lib libTICPUBLT_TI ${D}${libdir}
}
