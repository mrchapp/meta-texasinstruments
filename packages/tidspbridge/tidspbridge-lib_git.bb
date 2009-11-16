DESCRIPTION = "Texas Instruments MPU Bridge API"
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "virtual/kernel "
inherit pkgconfig autotools

PR = "r4"
PV = "0.0+git+${SRCREV}"


SRC_URI = "git://sealion.sc.ti.com/git/userspace-syslink.git;branch=syslink-bridge-devsdc;protocol=git \
           file://dynreg-makefile.patch \
"
SRCREV = "8457b8a302945d57319123726b65c297cd493daa"
S = "${WORKDIR}/git/bridge"

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev"
FILES_${PN} += "${libdir}/libbridge.so ${libdir}/libqos.a"
FILES_${PN}-dev += "${libdir}/libbridge.so.2 ${libdir}/libqos.so.2 ${libdir}/lib*.so"
FILES_${PN}-dbg += "${libdir}/.debug/"

do_stage() {
        autotools_stage_all
}

