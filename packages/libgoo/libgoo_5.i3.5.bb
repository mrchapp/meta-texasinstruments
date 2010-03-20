SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r3"

SRCREV = "89a8a808fbda8f20c917d290a8b9bb9e62ba9c11"
SRC_URI = "git://git.omapzoom.org/repo/libgoo.git;protocol=http"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
