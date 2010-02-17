SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r1"

SRCREV = "4b344bd9ddf72186fb2ae7df2fdde754116e95b8"
SRC_URI = "git://git.omapzoom.org/repo/libgoo.git;protocol=http;branch=libgoo-5.i3.4-rc"
SRC_URI += "file://mp3deadlock.patch;patch=1"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
