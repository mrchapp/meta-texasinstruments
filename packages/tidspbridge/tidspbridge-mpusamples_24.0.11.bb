PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU Bridge Samples."
LICENSE = "GPL"
PR = "r0"
DEPENDS = " \
	tidspbridge-lib \
        "

PACKAGES = "${PN}-dev ${PN}-dbg ${PN}"
FILES_${PN}-dbg = "/dspbridge/.debug"
FILES_${PN} = "/dspbridge"

inherit ccasefetch 

CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_MPU/dspbridge/...  L-BRIDGE-MPU_RLS_${PV}%\
	element /vobs/wtbu/OMAPSW_MPU/dspbridge/... COMPONENT_ROOT%\
	element * /main/LATEST%\
	"
CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_MPU/dspbridge"
CCASE_PATHCOMPONENT = "dspbridge"
CCASE_PATHCOMPONENTS = "3"



do_compile() {

	oe_libinstall -so -C ${STAGING_LIBDIR} libbridge ${S}/samples/mpu/lib/
	oe_libinstall -so -C ${STAGING_LIBDIR} libqos ${S}/samples/mpu/lib/
	oe_libinstall -so -C ${STAGING_LIBDIR} libbridge ${S}/target/lib/
	oe_libinstall -so -C ${STAGING_LIBDIR} libqos ${S}/target/lib/

	cd ${S}/samples/mpu/src

        oe_runmake  \
                PREFIX=${S} PROJROOT=${S}/samples/mpu \
                CROSS=${AR%-*}- -f Makefile clean

	oe_runmake  \
		PREFIX=${S} PROJROOT=${S}/samples/mpu \
		CROSS=${AR%-*}- -f Makefile

        oe_runmake  \
                PREFIX=${S} PROJROOT=${S}/samples/mpu \
                CROSS=${AR%-*}- -f Makefile install

}

do_stage() {
	cd ${S}/mpu_driver
	install -d ${STAGING_INCDIR}/dspbridge/mpu_driver
        tar -C inc -cf - . | tar -C ${STAGING_INCDIR}/dspbridge/mpu_driver -xvf -	

	install -d ${STAGING_BINDIR}/dspbridge/samples

	oenote "Installing samples in ${STAGING_BINDIR}/dspbridge/samples "
	install -m 0755 ${S}/target/dspbridge/*.out ${STAGING_BINDIR}/dspbridge/samples
	#install -m 0755 ${S}/samples/utils/install_bridge ${STAGING_BINDIR}/dspbridge/samples
	#install -m 0755 ${S}/samples/utils/install_bridge_128 ${STAGING_BINDIR}/dspbridge/samples
	#install -m 0755 ${S}/samples/utils/uninstall_bridge ${STAGING_BINDIR}/dspbridge/samples
}

do_install() {
	install -d ${D}/dspbridge
	install -m 0755 ${S}/target/dspbridge/*.out ${D}/dspbridge/
}
