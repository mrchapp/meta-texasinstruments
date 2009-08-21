SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "gst-plugins-base"
DESCRIPTION = "GStreamer RTSP Server"
LICENSE = "LGPL"
PR = "r0"

SRC_URI = "http://people.freedesktop.org/~wtay/gst-rtsp-0.10.4.tar.bz2"

inherit autotools pkgconfig

FILES_${PN} += "${libdir}/libgstrtsp*.so* {bindir}/"
FILES_${PN}-dev += "${libdir}/libgstrtsp.*a ${libdir}/pkgconfig"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
	autotools_stage_all
}

do_install2() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/examples/.libs/test-launch	${D}/${bindir}/
	install -m 0755 ${S}/examples/.libs/test-mp4	${D}/${bindir}/
	install -m 0755 ${S}/examples/.libs/test-ogg	${D}/${bindir}/
	install -m 0755 ${S}/examples/.libs/test-readme	${D}/${bindir}/
	install -m 0755 ${S}/examples/.libs/test-sdp	${D}/${bindir}/
	install -m 0755 ${S}/examples/.libs/test-video	${D}/${bindir}/
}

addtask install2 after do_install before do_package
