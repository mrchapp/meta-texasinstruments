SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r5"

SRCREV = "f49a39846d15dc21eaf0b200ec6b14eeb8253c1d"
SRC_URI = "git://git.omapzoom.org/repo/libgoo.git;protocol=http"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
