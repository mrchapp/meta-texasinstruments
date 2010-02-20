DEPENDS = "gst-plugins-base virtual/openmax-il"
PR = "r13"

SRC_URI = "git://github.com/roopar/gst-openmax.git;protocol=git;branch=omap"
SRC_URI += "\
	file://0001-DON-T-MERGE-temporary-dirty-hack-to-avoid-memcpy-wit.patch;patch=1 \
	file://0002-support-to-set-cropping-in-sink-element.patch;patch=1 \
	file://0003-some-hacks-for-playbin.patch;patch=1 \
	file://0004-temporary-hack-for-cases-where-omx-component-does-no.patch;patch=1 \
	file://element-rank.patch;patch=1 \
	"

# From omap branch:
SRCREV = "97ddc95f9d7c7e9e6c6cafa085596e1dabeca10d"
S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF += "--disable-valgrind"

do_patch2() {
	echo ${SRCREV} > ${S}/.version
}

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstomx.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstomx.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"

addtask patch2 after do_patch before do_configure
