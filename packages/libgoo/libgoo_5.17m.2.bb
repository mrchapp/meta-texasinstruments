SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r0"

SRCREV = "ea0ea9878b296179f2ef66ffe300cd3735d3dc70"
SRC_URI = "git://git.omapzoom.org/repo/libgoo.git;protocol=http;branch=libgoo-5.17m.2-rc"
S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
