DEPENDS = "tiopenmax-core tiopenmax-osal tisyslink-lib titiler-d2cmap titiler-memmgr"
DESCRIPTION = "Texas Instruments OpenMAX IL DOMX proxy components."
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
PR = "r0"

SRC_URI = "git://sealion.sc.ti.com/git/domx.git;protocol=git;branch=devel_robclark"
SRCREV = "1d593168ac22c1b91b5ae41da3290e09b4cc8b8e"
PV = "0.0+git+${SRCREV}"
S = "${WORKDIR}/git/"

EXTRA_OECONF = "--enable-tests"

inherit pkgconfig autotools

FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
	autotools_stage_all
}
