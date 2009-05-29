PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU Bridge Samples and API."
LICENSE = "GPL"
PR = "r0"
DEPENDS = " \
	linux-tiomap \
        "
PACKAGES = "${PN}"
FILES_${PN}="/dspbridge"


inherit ccasefetch

CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_MPU/dspbridge/... COMPONENT_ROOT%\
	element /vobs/wtbu/OMAPSW_MPU/dspbridge/...  L-BRIDGE-MPU_RLS_${PV}%\
	element * /main/LATEST%\
	"
CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_MPU/dspbridge"
CCASE_PATHCOMPONENT = "dspbridge"
CCASE_PATHCOMPONENTS = "3"

do_compile() {

        cd ${S}/mpu_api/src
        oe_runmake KRNLSRC=${STAGING_KERNEL_DIR} \
                PREFIX=${S} PROJROOT=${S}/mpu_api \
                ROOTFSDIR=${D}/dspbridge \
                CROSS=${AR%-*}- -f Makefile

	cp bridge/libbridge.so ${S}/target/lib/
	cp bridge/libbridge.so ${S}/samples/mpu/lib/
	cp qos/libqos.* ${S}/target/lib/
	cp qos/libqos.* ${S}/samples/mpu/lib/

	cd ${S}/samples/mpu/src
	oe_runmake KRNLSRC=${STAGING_KERNEL_DIR} \
		PREFIX=${S} PROJROOT=${S}/samples/mpu \
		ROOTFSDIR=${S} \
		CROSS=${AR%-*}- -f Makefile
#	BUILD=rel CMDDEFS='GT_TRACE DEBUG' \
#	PROJROOT=${S}/samples
}

do_stage() {
	cd ${S}/mpu_driver
	install -d ${STAGING_INCDIR}/dspbridge
        tar -C inc -cf - . | tar -C ${STAGING_INCDIR}/dspbridge -xvf -	
}

do_install() {
	install -d ${STAGING_BINDIR}/dspbridge/samples
        cd ${S}/mpu_api/src
        oe_runmake KRNLSRC=${STAGING_KERNEL_DIR} \
                PREFIX=${S} PROJROOT=${S}/mpu_api \
                ROOTFSDIR=${STAGING_BINDIR}/dspbridge/samples \
                CROSS=${AR%-*}- -f Makefile install


	cd ${S}/samples/mpu/src
        oe_runmake KRNLSRC=${STAGING_KERNEL_DIR} \
                PREFIX=${S} PROJROOT=${S}/samples/mpu \
                ROOTFSDIR=${STAGING_BINDIR}/dspbridge/samples \
                CROSS=${AR%-*}- -f Makefile install
	
	oenote "Installing samples in ${STAGING_BINDIR}/dspbridge/samples " 
	#install -m 0755 ${S}/target/dspbridge/*.out ${D}/dspbridge/samples/
	install -m 0755 ${S}/target/dspbridge/*.out ${STAGING_BINDIR}/dspbridge/samples
	install -m 0755 ${S}/samples/utils/install_bridge ${STAGING_BINDIR}/dspbridge/samples
	install -m 0755 ${S}/samples/utils/install_bridge_128 ${STAGING_BINDIR}/dspbridge/samples
        install -m 0755 ${S}/samples/utils/uninstall_bridge ${STAGING_BINDIR}/dspbridge/samples

}
