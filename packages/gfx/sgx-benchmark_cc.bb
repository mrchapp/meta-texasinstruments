SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies SGX PowerVR Benchmark Package"
LICENSE = "GPL"
PR = "r0"
COMPATIBLE_MACHINE = "omap-4430sdp"
RDEPENDS = "sgx-lib-noxws libgcc libstdc++"

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN} ${PN}-dbg"
FILES_${PN} = "${bindir}/* ${libdir}/* ${sysconfdir}/* "
FILES_${PN}-dbg = "${bindir}/.debug/* ${libdir}/.debug/*"

PV = "0.0+cc+${SRCREV}"

CCASE_SPEC = "%\
	element * COMPONENT_ROOT%\
	element /vobs/wtbu/OMAPSW_GFX/... ${SRCREV}%\
"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_GFX/IMAGINATION/GFX/GFX_Linux_BP"
CCASE_PATHCOMPONENT = "GFX_Linux_BP"
CCASE_PATHCOMPONENTS = "5"

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${S}/OGLES2/BenchmarkPackage/GridMark3/LinuxOMAP4/Raw/OGLES2GridMark3 ${D}${bindir}
    install -m 755 ./OGLES2/BenchmarkPackage/VillageMark/LinuxOMAP4/Raw/OGLES2VillageMark ${D}${bindir}
    install -m 755 ./OGLES2/BenchmarkPackage/TMark/LinuxOMAP4/Raw/OGLES2TMark ${D}${bindir}
    install -m 755 ./OGLES2/BenchmarkPackage/FableMark/LinuxOMAP4/Raw/OGLES2FableMark ${D}${bindir}
    install -m 755 ./OGLES/BenchmarkPackage/GridMark3/LinuxOMAP4/Raw/OGLESGridMark3 ${D}${bindir}
    install -m 755 ./OGLES/BenchmarkPackage/VillageMark/LinuxOMAP4/Raw/OGLESVillageMark ${D}${bindir}
    install -m 755 ./OGLES/BenchmarkPackage/TMark/LinuxOMAP4/Raw/OGLESTMark ${D}${bindir}
    install -m 755 ./OGLES/BenchmarkPackage/UIMark/PVR2D_SGX/UIMarkPVR2D_SGX ${D}${bindir}
    install -m 755 ./OVG/BenchmarkPackage/Mark/LinuxOMAP4/Raw/OVGMark ${D}${bindir}
    install -m 755 ./OVG/BenchmarkPackage/TigerMark/LinuxOMAP4/Raw/OVGTigerMark ${D}${bindir}
}


