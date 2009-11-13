DESCRIPTION = "Texas Instruments MPU Bridge API"
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "virtual/kernel "
inherit pkgconfig autotools

PR = "r5"
PV = "0.0+git+${SRCREV}"


SRC_URI = "git://dev.omapzoom.org/pub/scm/tisyslink/userspace-syslink.git;branch=syslink-bridge-devsdc;protocol=git \
           file://dynreg-makefile.patch \
"
S = "${WORKDIR}/git/bridge"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev += "${libdir}/*.*a ${libdir}/pkgconfig/"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
        autotools_stage_all
		cp ${S}/api/inc/* ${STAGING_INCDIR}
}

