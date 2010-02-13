DESCRIPTION = "GStreamer is a multimedia framework for encoding and decoding video and sound. \
It supports a wide range of formats including mp3, ogg, avi, mpeg and quicktime."
SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "LGPL"
HOMEPAGE = "http://www.gstreamer.net/"
PR = "r7"
DEPENDS = "glib-2.0 gettext-native libxml2 bison-native flex-native"

inherit autotools pkgconfig

SRC_URI = "git://gitorious.org/robclark-gstreamer/${PN};branch=l24.3;protocol=git \
           file://common-20091119.tar.gz \
          "
SRCREV = "489a4e4275854ddaedf81182237a1a0d6d800850"
S = "${WORKDIR}/git"

EXTRA_OECONF = "--disable-docs-build --disable-dependency-tracking --with-check=no --disable-examples --disable-tests --disable-valgrind --disable-debug --with-buffer-alignment=128"


do_configure_prepend() {
	# This m4 file contains nastiness which conflicts with libtool 2.2.2
	rm -f ${S}/common/m4/lib-link.m4 || true
	mv ${WORKDIR}/common-20091119/* ${S}/common/
	(cd ${S}/; NOCONFIGURE=1 ./autogen.sh)
}

#do_compile_prepend () {
#	mv ${WORKDIR}/gstregistrybinary.[ch] ${S}/gst/
#}

PARALLEL_MAKE = ""

do_stage() {
	autotools_stage_all
}

FILES_${PN} += " ${libdir}/gstreamer-0.10/*.so ${libexecdir}/gstreamer-0.10/*"
FILES_${PN}-dev += " ${libdir}/gstreamer-0.10/*.la ${libdir}/gstreamer-0.10/*.a"
FILES_${PN}-dbg += " ${libdir}/gstreamer-0.10/.debug/* ${libexecdir}/gstreamer-0.10/.debug/*"
