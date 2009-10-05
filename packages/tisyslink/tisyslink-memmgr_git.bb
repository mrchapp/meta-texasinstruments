DESCRIPTION = "TI MemMgr Library"
LICENSE = "LGPL"
SECTION = "libs"
PR = "r0"

DEPENDS = "linux-tiomap "

SRC_URI = "git://dev.omapzoom.org/pub/scm/tiler/tiler-userspace.git;protocol=git"

#ENABLE_UNIT_TESTS=--enable-unit-tests
#ENABLE_TILERMGR=--enable-tilermgr
TILERMGR = true

S = "${WORKDIR}/git/memmgr"

inherit autotools pkgconfig

do_stage() {
        autotools_stage_all
}

