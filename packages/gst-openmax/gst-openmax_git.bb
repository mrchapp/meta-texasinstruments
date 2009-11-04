DEPENDS = "gst-plugins-base virtual/openmax-il"
PR = "r7"

SRC_URI = "git://github.com/roopar/gst-openmax.git;protocol=git;branch=omap"
# From omap branch:
SRCREV = "f1e9f392840edf8cc4228052ed699af56d53eb6d"
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
