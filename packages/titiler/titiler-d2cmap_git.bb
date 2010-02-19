DESCRIPTION = "TI Ducati2Chiron Map Library"
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "titiler-memmgr tisyslink-lib"
inherit autotools pkgconfig

PR = "r7"
PV = "0.00+git+${SRCREV}"

SRC_URI = "git://dev.omapzoom.org/pub/scm/tiler/tiler-userspace.git;protocol=git"
S = "${WORKDIR}/git/d2c"

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
