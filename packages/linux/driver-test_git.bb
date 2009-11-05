DESCRIPTION = "Texas Instruments Device Driver Test Suite"
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

DEPENDS = "virtual/kernel"
inherit  pkgconfig

PR = "r1"
PV = "0.00+git+${SRCREV}"

SRC_URI = "git://dev.omapzoom.org/pub/scm/richo/device_driver_test.git;protocol=git "

S = "${WORKDIR}/git/"

PACKAGES = "${PN} ${PN}-dbg"
FILES_${PN} = "/testsuites/i2c/bin/*"
FILES_${PN}-dbg += "/testsuites/*/*/.debug/ /testsuites/*/.debug/ /testsuites/.debug/"

do_compile() {
	export KDIR=${STAGING_KERNEL_DIR}
	export CROSS_COMPILE=${TARGET_PREFIX}
	export HOST=${TARGET_PREFIX}
	export TESTSUITES=`for i in ${S}/*; do echo "\`basename $i\` "; done`
	${S}/buildTestSuites.sh ${S}
}

do_install() {
}

