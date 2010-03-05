SECTION = "mulitimedia"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies Benchmark applications"
LICENSE = "GPL"
PR = "r0"
COMPATIBLE_MACHINE = "zoom(2|3)|omap-3(4|6)30(l|s)dp|beagleboard"
RDEPENDS = sgx-lib-noxws
DEPENDS = virtual/kernel

GFORGE_USER ?= ${USER} 

PV = "23.i3+git+${SRCREV}"
SRC_GFORGE = "ssh://${GFORGE_USER}@gforge01.dal.design.ti.com/gitroot/gfx_l23_bp"
S = "${WORKDIR}/git"

inherit gforgefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"
FILES_${PN} = "${bindir}/*/*"

do_install() {
    cd ${S}
    install -d ${D}${bindir}/GFX_BP
    install -m 755 ./OGLES/BenchmarkPackage/GridMark3/LinuxOMAP3/CommonRaw/OGLESGridMark3 ${D}${bindir}/GFX_BP
    install -m 755 ./OGLES/BenchmarkPackage/TMark/LinuxOMAP3/CommonRaw/OGLESTMark ${D}${bindir}/GFX_BP
    install -m 755 ./OGLES/BenchmarkPackage/VillageMark/LinuxOMAP3/CommonRaw/OGLESVillageMark ${D}${bindir}/GFX_BP
    install -m 755 ./OGLES2/BenchmarkPackage/FableMark/LinuxOMAP3/Raw/OGLES2FableMark ${D}${bindir}/GFX_BP
    install -m 755 ./OGLES2/BenchmarkPackage/GridMark3/LinuxOMAP3/Raw/OGLES2GridMark3 ${D}${bindir}/GFX_BP
    install -m 755 ./OGLES2/BenchmarkPackage/TMark/LinuxOMAP3/Raw/OGLES2TMark ${D}${bindir}/GFX_BP
    install -m 755 ./OGLES2/BenchmarkPackage/VillageMark/LinuxOMAP3/Raw/OGLES2VillageMark ${D}${bindir}/GFX_BP
    install -m 755 ./OVG/BenchmarkPackage/Mark/LinuxOMAP3/Raw/OVGMark ${D}${bindir}/GFX_BP
    install -m 755 ./OVG/BenchmarkPackage/TigerMark/LinuxOMAP3/Raw/OVGTigerMark ${D}${bindir}/GFX_BP
}



