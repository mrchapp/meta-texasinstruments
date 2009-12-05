DEPENDS = "gst-plugins-base virtual/openmax-il"
PR = "r9"

SRC_URI = "git://github.com/roopar/gst-openmax.git;protocol=git;branch=omap"

# From omap branch:
SRCREV = "e6df6b6242cc3708c286a5f4c784b4ed68ece1d1"
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
