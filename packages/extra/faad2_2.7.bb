SECTION = "multimedia"
DESCRIPTION = "Freeware Advanced Audio (AAC) Decoder including SBR decoding"
HOMEPAGE = "http://www.audiocoding.com"
PR = "r0"

inherit autotools

SRC_URI = "${SOURCEFORGE_MIRROR}/faac/${PN}-${PV}.tar.gz"

do_stage() {
	autotools_stage_all
}

do_install() {
	oe_runmake install DESTDIR=${D}
}
