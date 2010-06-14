SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r9"

SRC_URI = "git://github.com/mrchapp/libgoo.git;protocol=http;branch=libgoo-5.i3.8-rc"
SRCREV = "fe02d8868d462f83438c47728f3a4e05f1dfd93a"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
