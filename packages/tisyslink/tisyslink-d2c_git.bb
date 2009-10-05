DESCRIPTION = "TI Ducati2Chiron Map Library"
LICENSE = "LGPL"
SECTION = "libs"
DEPENDS = "tisyslink-memmgr tisyslink-lib"
PR = "r0"

SRC_URI = "git://dev.omapzoom.org/pub/scm/tiler/tiler-userspace.git;protocol=http"
S = "${WORKDIR}/git/d2c"

inherit autotools pkgconfig

do_stage() {
        autotools_stage_all
}

