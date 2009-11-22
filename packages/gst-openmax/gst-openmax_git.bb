DEPENDS = "gst-plugins-base virtual/openmax-il"
PR = "r9"

SRC_URI = "git://github.com/roopar/gst-openmax.git;protocol=git;branch=omap"
SRC_URI += "file://0001-Add-extra-TI-error-codes-for-debugging.patch;patch=1"
SRC_URI += "file://0003-work-around-for-bug-in-OMX-returning-errors-in-case-.patch;patch=1"
SRC_URI += "file://0004-Fix-for-allocating-buffers-when-the-first-buffer_all.patch;patch=1"
SRC_URI += "file://0005-add-support-for-dynamic-port-enable-disable.patch;patch=1"
#SRC_URI += "file://0006-camera-add-support-for-image-capture-and-switching-b.patch;patch=1"
SRC_URI += "file://0007-omap4-component-names.patch;patch=1"
SRC_URI += "file://0008-omap4-for-now-use-OMX_AllocateBuffer-by-default.patch;patch=1"
SRC_URI += "file://0009-remove-omap3-specific-custom-index-s.patch;patch=1"
SRC_URI += "file://0010-add-NV12-support.patch;patch=1"
SRC_URI += "file://0011-hard-code-NV12-instead-of-UYVY-for-now.patch;patch=1"
SRC_URI += "file://0012-fix-potential-segfault.patch;patch=1"

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
