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
FILES_${PN} = "${bindir}/* "
FILES_${PN}-dbg = "${bindir}/.debug/* "

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
    for fil in \
        OGLES2/BenchmarkPackage/GridMark3/LinuxOMAP4/Raw/OGLES2GridMark3 \
        OGLES2/BenchmarkPackage/VillageMark/LinuxOMAP4/Raw/OGLES2VillageMark \
        OGLES2/BenchmarkPackage/TMark/LinuxOMAP4/Raw/OGLES2TMark \
        OGLES2/BenchmarkPackage/FableMark/LinuxOMAP4/Raw/OGLES2FableMark \
        OGLES/BenchmarkPackage/GridMark3/LinuxOMAP4/Raw/OGLESGridMark3 \
        OGLES/BenchmarkPackage/VillageMark/LinuxOMAP4/Raw/OGLESVillageMark \
        OGLES/BenchmarkPackage/TMark/LinuxOMAP4/Raw/OGLESTMark \
        OGLES/BenchmarkPackage/UIMark/PVR2D_SGX/UIMarkPVR2D_SGX \
        OVG/BenchmarkPackage/Mark/LinuxOMAP4/Raw/OVGMark \
        OVG/BenchmarkPackage/TigerMark/LinuxOMAP4/Raw/OVGTigerMark ;
    do
        install -m 755 ${fil} ${D}${bindir}
    done
}

