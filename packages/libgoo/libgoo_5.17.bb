SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il ${TTIF_DEPENDS}"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r0${TTIF_PR}"

SRCREV = "f132c78fae01d53b267dd6f950e4df7ea0650398"
SRC_URI = "git://git.omapzoom.org/repo/libgoo.git;protocol=http;branch=libgoo-5.17m.3-rc"
SRC_URI += "file://mp3deadlock.patch;patch=1"
SRC_URI += " ${@base_contains("DISTRO_FEATURES", "ttif", "file://ttif.patch;patch=1", "", d)} "
S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}

