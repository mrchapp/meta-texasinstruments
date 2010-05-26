SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r7"

SRC_URI = "git://git.omapzoom.org/repo/libgoo.git;protocol=http;tag=v${PV}"

# Temporarily add this work for JPEG Encoder and PPLIB:
SRC_URI +="file://0001-jpegenc-Implement-functions-from-PPLIB.patch;patch=1"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
