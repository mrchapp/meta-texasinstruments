SECTION = "libs"
DEPENDS = "glib-2.0 virtual/openmax-il ${TTIF_DEPENDS}"
DESCRIPTION = "Library for interacting OpenMAX IL."
LICENSE = "LGPL"
PR = "r0${TTIF_PR}"

SRCREV = "f0953c7bc387877e1baaaeaf2ebede74299106cc"
SRC_URI = "git://git.omapzoom.org/repo/libgoo.git;protocol=http"
SRC_URI += " ${@base_contains("DISTRO_FEATURES", "ttif", "file://ttif.patch;patch=1", "", d)} "
S = "${WORKDIR}/git"

EXTRA_OECONF = "--enable-ti-camera --enable-ti-clock"

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}

