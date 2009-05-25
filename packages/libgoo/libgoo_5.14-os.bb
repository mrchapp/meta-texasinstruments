SECTION = "libs"
DEPENDS = "glib-2.0 tiopenmax"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r0"

SRCREV = "d3298e6307c31c3154ff701bce2cafddc81d7d18"
SRC_URI = "git://github.com/mrchapp/libgoo.git;protocol=http;branch=master"
SRC_URI += "file://mp3nolayer2.patch;patch=1"
S = "${WORKDIR}/git"

EXTRA_OECONF = "--disable-ti-camera --disable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}
