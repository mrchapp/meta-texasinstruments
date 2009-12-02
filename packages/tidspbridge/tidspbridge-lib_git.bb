DESCRIPTION = "Texas Instruments MPU Bridge API"
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "virtual/kernel "
inherit pkgconfig autotools

PR = "r7"
PV = "0.0+git+${SRCREV}"

SRC_URI = "git://dev.omapzoom.org/pub/scm/tisyslink/userspace-syslink.git;branch=syslink-bridge-24.1;protocol=git "
S = "${WORKDIR}/git/bridge"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
FILES_${PN} += "${libdir}/libbridge.so"
FILES_${PN}-dev += "${libdir}/libbridge.so.2 ${libdir}/libqos.so.2 ${libdir}/lib*.so ${libdir}/libqos.a"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
        autotools_stage_all
}

