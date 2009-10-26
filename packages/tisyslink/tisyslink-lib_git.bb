DESCRIPTION = "Texas Instruments Syslink libraries."
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "virtual/kernel tisyslink-memmgr"
inherit  pkgconfig autotools

PR = "r6"
PV = "0.00+git+${SRCREV}"

SRC_URI = "\
    git://dev.omapzoom.org/pub/scm/tisyslink/userspace-syslink.git;protocol=git \
    file://install_syslink.patch;patch=1 \
    file://tiler_makefile.patch;patch=1 \
    file://proc_sample_removal.patch;patch=1 \
"

S = "${WORKDIR}/git/syslink"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
FILES_${PN} += "${libdir}/*.so /dspbridge/install_syslink"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

#EXTRA_OECONF += "--enable-tilermgr --enable-unit-tests"
export TILER_INC_PATH="${STAGING_DIR}/armv7a-none-linux-gnueabi/usr/lib/"
#export KRNLSRC="/work/kernel-omap4"

do_stage() {
        autotools_stage_all
}

do_install_append() {
	install -d ${D}/dspbridge
	install -m 0777 ${S}/scripts/install_syslink ${D}/dspbridge
}


