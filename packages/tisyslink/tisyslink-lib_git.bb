DESCRIPTION = "Texas Instruments Syslink libraries."
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "virtual/kernel titiler-memmgr"
inherit  pkgconfig autotools

PR = "r11"
PV = "0.0+git+${SRCREV}"

SRC_URI = "\
    git://dev.omapzoom.org/pub/scm/tisyslink/userspace-syslink.git;branch=syslink-bridge-24.1;protocol=git \
    file://install_syslink.patch;patch=1 \
    file://tiler_makefile.patch;patch=1 \
"
S = "${WORKDIR}/git/syslink"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
FILES_${PN} += "${libdir}/*.so ${bindir}/*"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

export TILER_INC_PATH="${STAGING_DIR}/armv7a-none-linux-gnueabi/usr/lib/"

do_stage() {
        autotools_stage_all
}

do_install_append() {
	install -m 0777 ${S}/scripts/install_syslink ${D}${bindir}
}


