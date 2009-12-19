DESCRIPTION = "Texas Instruments OpenMAX IL DOMX proxy components."

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
PR = "r1"
DEPENDS = "tiopenmax-core tiopenmax-osal tisyslink-lib titiler-d2cmap titiler-memmgr"

SRC_URI = "git://sealion.sc.ti.com/git/domx.git;protocol=git"
PV = "0.0+git+${SRCREV}"
S = "${WORKDIR}/git/"

EXTRA_OECONF = "--enable-tests"

inherit pkgconfig autotools

FILES_${PN} += "${libdir}/*.so ${bindir}/*"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/ ${bindir}/.debug/"

do_stage() {
    autotools_stage_all
}
