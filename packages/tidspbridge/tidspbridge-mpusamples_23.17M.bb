PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU Bridge Samples."
LICENSE = "GPL"
PR = "r0"
DEPENDS = "tidspbridge-lib"

PACKAGES = "${PN}-dev ${PN}-dbg ${PN}"
FILES_${PN}-dbg = "/dspbridge/.debug"
FILES_${PN} = "/dspbridge/*"

do_compile() {
        cp -r ${STAGING_DIR_HOST}/mpu_api_samples/* ${S}
        oe_libinstall -so -C ${STAGING_LIBDIR} libbridge ${S}/samples/mpu/lib/
        oe_libinstall -so -C ${STAGING_LIBDIR} libqos ${S}/samples/mpu/lib/
        oe_libinstall -so -C ${STAGING_LIBDIR} libbridge ${S}/target/lib/

	cd ${S}/samples/mpu/src
	oe_runmake \
		PREFIX=${S} PROJROOT=${S}/samples/mpu \
		BUILD=rel CMDDEFS='GT_TRACE DEBUG'
}

do_install() {
	install -d ${D}/dspbridge
	install -m 0755 ${S}/samples/mpu/src/bridged/bridged ${D}/dspbridge
	install -m 0755 ${S}/samples/mpu/src/*/*.out ${D}/dspbridge
}
