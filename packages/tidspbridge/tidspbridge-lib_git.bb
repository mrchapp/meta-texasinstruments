DESCRIPTION = "Texas Instruments MPU Bridge API"
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "virtual/kernel "
inherit pkgconfig autotools

PR = "r3"
PV = "0.0+git+${SRCREV}"


SRC_URI = "git://dev.omapzoom.org/pub/scm/tisyslink/userspace-syslink.git;protocol=git \
           file://dynreg-makefile.patch \
"
S = "${WORKDIR}/git/bridge"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
FILES_${PN} += "${libdir}/libbridge.so ${libdir}/libqos.a"
FILES_${PN}-dev += "${libdir}/libbridge.so.2 ${libdir}/libqos.so.2 ${libdir}/lib*.so"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
        autotools_stage_all
		cp ${S}/api/inc/* ${STAGING_INCDIR}
}

