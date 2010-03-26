SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r4"

SRCREV = "4d60958c991d89e61c6d67805c0c9e334d1b62ee"
SRC_URI = "git://git.omapzoom.org/repo/libgoo.git;protocol=http"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
