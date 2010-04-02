DESCRIPTION = "TI MemMgr Library"
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "virtual/kernel"
inherit autotools pkgconfig

PR = "r7"
PV = "0.00+git+${SRCREV}"

SRC_URI = "git://git.omapzoom.org/platform/hardware/ti/tiler.git;protocol=git"
S = "${WORKDIR}/git/memmgr"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

EXTRA_OECONF += "--enable-tilermgr --enable-tests"

do_compile_prepend() {
    ln -s ${S}/../utils/testlib.c ${S}/tests/testlib.c
}

do_stage() {
        autotools_stage_all
}

