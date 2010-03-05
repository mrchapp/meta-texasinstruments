DEPENDS = "gst-plugins-base gst-goo"
DESCRIPTION = "A simple application to measure JPEG capture times."
PR = "r0"

SRC_URI = "file://shot.c"
S = ${WORKDIR}

do_compile() {
	${CC} `pkg-config --libs --cflags gstreamer-0.10` -o shot shot.c
}

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/shot ${D}/${bindir}/shot
}
