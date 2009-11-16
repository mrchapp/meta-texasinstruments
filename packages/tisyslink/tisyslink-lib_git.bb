DESCRIPTION = "Texas Instruments Syslink libraries."
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "virtual/kernel titiler-memmgr"
inherit  pkgconfig autotools

PR = "r8"
PV = "0.0+git+${SRCREV}"

SRC_URI = "\
    git://sealion.sc.ti.com/git/userspace-syslink.git;branch=syslink-bridge-devsdc;protocol=git \
    file://install_syslink.patch;patch=1 \
    file://tiler_makefile.patch;patch=1 \
"
SRCREV = "1e79a1584c9495a695e6924aeb295075e2582d74"

S = "${WORKDIR}/git/syslink"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
FILES_${PN} += "${libdir}/*.so /dspbridge/install_syslink"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

export TILER_INC_PATH="${STAGING_DIR}/armv7a-none-linux-gnueabi/usr/lib/"

do_stage() {
        autotools_stage_all
}

do_install_append() {
	install -d ${D}/dspbridge
	install -m 0777 ${S}/scripts/install_syslink ${D}/dspbridge
}


