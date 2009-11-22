DESCRIPTION = "TI Ducati2Chiron Map Library"
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "titiler-memmgr tisyslink-lib"
inherit autotools pkgconfig

PR = "r5"
PV = "0.00+git+${SRCREV}"

SRC_URI = "git://sealion.sc.ti.com/git/tiler-userspace.git;branch=memmgr_dev;protocol=git"
SRCREV = "ea7b70c78c6a82d96467f6b29c996f78b79d7b96"
#SRC_URI = "git://dev.omapzoom.org/pub/scm/tiler/tiler-userspace.git;branch=memmgr_dev;protocol=git"
S = "${WORKDIR}/git/d2c"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
        autotools_stage_all
}
