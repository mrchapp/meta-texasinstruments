DEPENDS = "gst-plugins-base virtual/openmax-il"
PR = "r8"

SRC_URI = "git://github.com/roopar/gst-openmax.git;protocol=git;branch=omap"
# This patch holds the camera's horses until OMX is ready for it.
SRC_URI += "file://revert-cam-video.patch;patch=1"

# From omap branch:
SRCREV = "bbc6e3fd66f41057bdf8c3ea08902883ec0f48a1"
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
