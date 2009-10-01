SECTION = "multimedia"
DESCRIPTION = "Library of OpenCORE Framework implementation of Adaptive Multi Rate Narrowband and Wideband speech codec"
PR = "r0"

inherit autotools pkgconfig

SRC_URI = "${SOURCEFORGE_MIRROR}/opencore-amr/opencore-amr-${PV}.tar.gz"

do_stage() {
	autotools_stage_all
}
