PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge Socket Node compilation."
LICENSE = "LGPL"
PR = "r0"
#RDEPENDS = "tidspbridge-mpusamples"
DEPENDS = "tidspbridge-mpusamples \
	   tidspbridge-bios \
	   tidspbridge-cgt7x \
           tidspbridge-fc \
           tidspbridge-xdc \
	   tidspbridge-ipc \
	   tidspbridge-lib \
	   tidspbridge-dllcreate"
FILES_${PN}="/dspbridge"

CCASE_SPEC = "%\
	      element /vobs/WTSD_MultiCoreSW/Tesla/Bridge/... BRIDGE-DSP_RLS_${PV}%\
	      element /vobs/WTSD_MultiCoreSW/Tesla/Bridge/... -error%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/WTSD_MultiCoreSW/Tesla/Bridge"
CCASE_PATHCOMPONENT = "Bridge"
CCASE_PATHCOMPONENTS = "3"

ENV_VAR = "DEPOT=${STAGING_BINDIR}/dspbridge/tools \
	   SABIOS_DIR=${STAGING_BINDIR}/dspbridge/tools/bios_6_20_00_23/packages \
	   BIOS_OPTS=SABIOS_DIR=${STAGING_BINDIR}/dspbridge/tools/bios_6_20_00_23/packages \
	   DD_XDCDIR=${STAGING_BINDIR}/dspbridge/tools/xdctools_3_15_00_28 \
	   DD_XDC_OPT=DD_XDCDIR=${STAGING_BINDIR}/dspbridge/tools/xdctools_3_15_00_28 DD_XDCOPTIONS="XDCOPTIONS=v XDCBUILDCFG=${S}/private.bld XDCPATH=${STAGING_BINDIR}/dspbridge/tools/bios_6_20_00_23/packages\;${STAGING_BINDIR}/dspbridge/tools/framework_components_3_00_00_30/fctools/packages\;${STAGING_BINDIR}/dspbridge/tools/framework_components_3_00_00_30/packages\;${S}/bdsptools/packages\;${STAGING_BINDIR}/dspbridge/tools/ipc_1_00_00_27/packages\;${S} XDCTARGETS=C64T" \
	   DLLCREATE_DIR=${STAGING_BINDIR}/DLLcreate \
"
SABIOS_DIR = ${STAGING_BINDIR}/dspbridge/tools/bios_6_20_00_23/packages
C6X_CODEGEN_ROOT = ${STAGING_BINDIR}/dspbridge/tools/cgt7x-7.0.0a09019

SRC_URI = "file://tidspbridge.patch;patch=1"

inherit ccasefetch

do_compile() {
	sed -e \
          's%SABIOS_DIR = \".*\";%SABIOS_DIR = \"${SABIOS_DIR}\"%' \
         -e 's%tiTargets.C64P.rootDir = \".*\" +%tiTargets.C64.rootDir = \"${C6X_CODEGEN_ROOT}\";%' \
         -e '/^[ ]*c6xCodeGenVersion/d' \
          ${S}/config.bld > ${S}/private.bld

	chmod -R +w ${S}/*
	${ENV_VAR} oe_runmake -f gmakefile .clean
	${ENV_VAR} oe_runmake -f gmakefile .bridge_samples XDCTARGETS=C64T
}

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/dsp
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/dsp
	install -d ${STAGING_LIBDIR}/dspbridge/exports/lib
	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/lib/*.a64T ${STAGING_LIBDIR}/dspbridge/exports/lib
	install -d ${STAGING_INCDIR}/dspbridge/exports/include
	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/include/*.h ${STAGING_INCDIR}/dspbridge/exports/include
}

do_install() {
	install -d ${D}/dspbridge/exports/lib
	install -m 0644 ${S}/ti/dspbridge/dsp/bridge_product/exports/lib/*.a64T ${D}/dspbridge/exports/lib
	cd ${S}/ti/dspbridge/dsp/samples
	install -m 0644 *.dof* ${D}/dspbridge
	install -m 0644 *.dll64T* ${D}/dspbridge
	install -d ${D}/dspbridge/exports/include
	oenote "Installing exports/include... "
	install -m 0644 ${STAGING_INCDIR}/dspbridge/*.h ${D}/dspbridge/exports/include
	oenote "Installing MPU API and Samples..."
	install -m 755 ${STAGING_BINDIR}/dspbridge/samples/*.out ${D}/dspbridge
	install -m 0755 ${STAGING_BINDIR}/dspbridge/samples/install_bridge ${D}/dspbridge
	install -m 0755 ${STAGING_BINDIR}/dspbridge/samples/install_bridge_128 ${D}/dspbridge
	install -m 0755 ${STAGING_BINDIR}/dspbridge/samples/uninstall_bridge ${D}/dspbridge
	oenote "Installing modules"
	install -m 0644 `find ${STAGING_LIBDIR}/modules -name bridgedriver.ko` ${D}/dspbridge
	install -m 0644 `find ${STAGING_LIBDIR}/modules -name notify_mailbox.ko` ${D}/dspbridge
	install -m 0644 `find ${STAGING_LIBDIR}/modules -name omap_notify.ko` ${D}/dspbridge
	install -m 0644 `find ${STAGING_LIBDIR}/modules -name ducati_enabler.ko` ${D}/dspbridge
}
