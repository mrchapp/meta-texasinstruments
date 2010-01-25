SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "GFX TICPUBLT 2D acceleration library"
LICENSE = "GPL"
PR = "r0"
COMPATIBLE_MACHINE = "omap-4430sdp"

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN} ${PN}-dbg"
FILES_${PN} = "${bindir}/* ${libdir}/*"
FILES_${PN}-dbg = "${bindir}/.debug/* ${libdir}/.debug/*"

PV = "0.0+cc+${SRCREV}"

CCASE_SPEC = "%\
    element /vobs/OMAPSW_TIBLT_2DGFX/... ${SRCREV}%\
"

CCASE_PATHFETCH = "/vobs/OMAPSW_TIBLT_2DGFX/TIBLT/release/Linux"
CCASE_PATHCOMPONENT = "Linux"
CCASE_PATHCOMPONENTS = "4"

do_compile() {
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

