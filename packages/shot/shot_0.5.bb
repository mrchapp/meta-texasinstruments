DEPENDS = "gst-plugins-base gst-goo"
DESCRIPTION = "A simple application to measure camera capture times."
PR = "r1"

SRC_URI = "git://github.com/mrchapp/gstcamera-measure.git;protocol=http;tag=v${PV}"
S = ${WORKDIR}/git

do_compile() {
	${CC} `pkg-config --libs --cflags gstreamer-0.10` -o shot shot.c
}

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/shot ${D}/${bindir}/shot
}
