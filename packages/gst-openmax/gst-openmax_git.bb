DEPENDS = "gst-plugins-base virtual/openmax-il"
PR = "r14"

SRC_URI = "git://gitorious.org/robclark-gstreamer/gst-openmax.git;protocol=git;branch=l24.3"

SRCREV = "214ca1bacd6fd7656bb5cfa312519ae98a3ec060"
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
