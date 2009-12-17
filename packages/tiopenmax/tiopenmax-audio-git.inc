SRC_URI = "git://dev.omapzoom.org/pub/scm/abraham/audio-omx.git;protocol=git"

PV = "0.0+git+${SRCREV}"
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"

FILES_${PN} += "${libdir}/*.so ${bindir}/*"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

