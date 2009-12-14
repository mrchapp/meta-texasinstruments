DEPENDS = "gst-plugins-base virtual/openmax-il"
PR = "r11"

SRC_URI = "git://github.com/roopar/gst-openmax.git;protocol=git;branch=omap"

# From omap branch:
SRCREV = "3d1d7d0b943ef447e0563c4fffda09b31dd77cca"
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
