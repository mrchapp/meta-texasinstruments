SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r0"

SRCREV = "6892d8b8ac19b72dac2f0e4d5b185338420c8f18"
SRC_URI = "git://git.omapzoom.org/repo/libgoo.git;protocol=http;branch=libgoo-5.16-rc"
S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
