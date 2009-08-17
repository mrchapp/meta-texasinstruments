PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU Bridge Samples and API."
LICENSE = "GPL"
PR = "r0"
DEPENDS = " \
	linux-tiomap \
        "
PACKAGES = "${PN}"
#FILES_${PN}="/dspbridge"
FILES_${PN} += "${libdir}/libbridge.so ${libdir}/libqos.a"
FILES_${PN}-dev = "${libdir}/libbridge.so.2 ${libdir}/libqos.so.2"


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

        cd ${S}/mpu_api/src
	oe_runmake \
                PREFIX=${S} PROJROOT=${S}/mpu_api \
                CROSS=${AR%-*}- -f Makefile clean

        oe_runmake \
                PREFIX=${S} PROJROOT=${S}/mpu_api \
                CROSS=${AR%-*}- -f Makefile

	oe_runmake  \
                PREFIX=${S} PROJROOT=${S}/mpu_api \
                CROSS=${AR%-*}- -f Makefile install

	cp -prf ${S}/target/lib/*  ${S}/samples/mpu/lib/

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

	oenote "Installing code from mpu_api to ${STAGING_INCDIR}/dspbridge/mpu_api"
	install -d ${STAGING_INCDIR}/dspbridge/mpu_api
	tar -C ${S}/mpu_api -cf - inc | tar -C ${STAGING_INCDIR}/dspbridge/mpu_api -xvf -
	
	oenote "Installing libraries in ${STAGING_LIBDIR} "
	oe_libinstall -so -C ${S}/target/lib libbridge ${STAGING_LIBDIR}
	oe_libinstall -so -a -C ${S}/target/lib libqos ${STAGING_LIBDIR}

	oenote "Installing libraries in ${STAGING_INCDIR}/dspbridge/lib "
	if [ ! -d ${STAGING_INCDIR}/dspbridge/lib ]
	then
		install -d ${STAGING_INCDIR}/dspbridge/lib
	fi
	oe_libinstall -so -C ${S}/target/lib libbridge ${STAGING_INCDIR}/dspbridge/lib
        oe_libinstall -so -a -C ${S}/target/lib libqos ${STAGING_INCDIR}/dspbridge/lib


	install -d ${STAGING_BINDIR}/dspbridge/samples

	oenote "Installing samples in ${STAGING_BINDIR}/dspbridge/samples "
	install -m 0755 ${S}/target/dspbridge/*.out ${STAGING_BINDIR}/dspbridge/samples
	#install -m 0755 ${S}/samples/utils/install_bridge ${STAGING_BINDIR}/dspbridge/samples
	#install -m 0755 ${S}/samples/utils/install_bridge_128 ${STAGING_BINDIR}/dspbridge/samples
	#install -m 0755 ${S}/samples/utils/uninstall_bridge ${STAGING_BINDIR}/dspbridge/samples

}

do_install() {
	oenote "Installing bridge libraries in ${D}/lib "
	oe_libinstall -so -C ${S}/target/lib libbridge ${D}${libdir}
	oe_libinstall -so -a -C ${S}/target/lib libqos ${D}${libdir}
}
