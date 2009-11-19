DEPENDS = "tiopenmax-core tiopenmax-osal tisyslink-lib titiler-d2cmap titiler-memmgr"
DESCRIPTION = "Texas Instruments OpenMAX IL DOMX proxy components."
PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
PR = "r0"

SRC_URI = "git://sealion.sc.ti.com/git/domx.git;protocol=git;branch=devel_robclark"
SRCREV = "c336bec7da9cb0aec5d80df20442f3137f163029"
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
