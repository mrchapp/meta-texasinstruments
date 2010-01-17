PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU Bridge Samples."
LICENSE = "GPL"
PR = "r1"
DEPENDS = " \
	tidspbridge-lib \
        "

PACKAGES = "${PN}-dev ${PN}-dbg ${PN}"
FILES_${PN}-dbg = "/dspbridge/.debug"
FILES_${PN} = "/dspbridge"

S = "${WORKDIR}/git"

PV = "23.0+git+${SRCREV}"

SRC_URI = "git://dev.omapzoom.org/pub/scm/tidspbridge/userspace-dspbridge.git;protocol=git;branch=master"

do_compile() {
	cd ${S}/source
	oe_runmake  KRNLSRC=${STAGING_KERNEL_DIR}  \
		 DEPOT=${STAGING_BINDIR_NATIVE}/dspbridge/tools .samples
}

do_stage() {
        echo "Nothing to stage - stage done by dspbridge-lib"
}

do_install() {
	install -d ${STAGING_BINDIR}/dspbridge/samples
	install -m 0755 ${S}/source/target/dspbridge/* ${STAGING_BINDIR}/dspbridge/samples
	
	oenote "Installing samples in ${STAGING_BINDIR}/dspbridge/samples " 
        install -d ${D}/dspbridge/samples
	install -m 0755 ${S}/source/target/dspbridge/*.out ${D}/dspbridge/samples
	install -m 0755 ${S}/source/samples/utils/install_bridge ${D}/dspbridge/samples
	install -m 0755 ${S}/source/samples/utils/install_bridge_128 ${D}/dspbridge/samples
        install -m 0755 ${S}/source/samples/utils/uninstall_bridge ${D}/dspbridge/samples
	
}
