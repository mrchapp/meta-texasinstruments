DESCRIPTION = "TI MemMgr Library"
LICENSE = "LGPL"
SECTION = "libs"
PR = "r1"

DEPENDS = "linux-tiomap "

SRC_URI = "git://dev.omapzoom.org/pub/scm/tiler/tiler-userspace.git;protocol=git "

EXTRA_OECONF += "--enable-tilermgr"

S = "${WORKDIR}/git/memmgr"

inherit autotools pkgconfig

do_stage() {
        autotools_stage_all
	cp -a ${S}/tilermgr.h ${STAGING_INCDIR}
}

