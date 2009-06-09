SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r0"

SRCREV = "f0953c7bc387877e1baaaeaf2ebede74299106cc"
SRC_URI = "git://git.omapzoom.org/repo/libgoo.git;protocol=http;branch=libgoo-5.17-rc"
S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
