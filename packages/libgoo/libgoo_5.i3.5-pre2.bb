SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r2"

SRCREV = "62f6299fe64e3b29d04649b41e095b6eec8f1270"
SRC_URI = "git://git.omapzoom.org/repo/libgoo.git;protocol=http;branch=libgoo-5.i3.5-rc"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
