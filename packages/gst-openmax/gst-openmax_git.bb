DEPENDS = "gstreamer virtual/openmax-il"
PR = "r2"

SRC_URI = "git://github.com/felipec/gst-openmax.git;protocol=git"
# From omap branch:
SRCREV = "87928514f42ffb1460f29881b21b75955044a87b"
S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF += "--disable-valgrind"

do_patch2() {
	echo ${SRCREV} > ${S}/.version
}

FILES_${PN} += "${libdir}/gstreamer-0.10/libgstomx.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstomx.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"

addtask patch2 after do_patch before do_compile
