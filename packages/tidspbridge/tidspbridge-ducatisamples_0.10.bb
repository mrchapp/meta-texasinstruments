PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP Ducati Samples."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "tidspbridge-cgtarm \
	   tidspbridge-bios \
	   tidspbridge-cgtarm \
           tidspbridge-fc \
           tidspbridge-xdc "

CCASE_SPEC = "%\
	      element /vobs/WTSD_MultiCoreSW/Ducati/Bridge/... BRIDGE-DUCATI_RLS_${PV}%\
	      element /vobs/WTSD_MultiCoreSW/Ducati/Bridge/... -error%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/WTSD_MultiCoreSW/Ducati/Bridge"
CCASE_PATHCOMPONENT = "Bridge"
CCASE_PATHCOMPONENTS = "3"

BIOS_VER = "6_20_02_42_eng"
XDC_VER = "3_15_03_64_eng"
FC_VER = "3_00_00_44_eng"
CGTARM_VER = "4.6.0B1"

DEPOT = ${STAGING_BINDIR}/dspbridge/tools
SABIOS_DIR = ${STAGING_BINDIR}/dspbridge/tools/bios_${BIOS_VER}/packages
XDC_DIR = ${STAGING_BINDIR}/dspbridge/tools/xdctools_${XDC_VER}
FC_DIR = ${STAGING_BINDIR}/dspbridge/tools/framework_components_${FC_VER}
CGTARM_DIR = ${STAGING_BINDIR}/dspbridge/tools/cgtarm-${CGTARM_VER}

XDCPATH=${SABIOS_DIR}\;${FC_DIR}/fctools/packages\;${FC_DIR}/packages\;${S}/bdsptools/packages\;${CGTARM_DIR}/packages\;${S}

ENV_VAR = "DEPOT=${DEPOT} \
	   SABIOS_DIR=${SABIOS_DIR} \
	   BIOS_OPTS=SABIOS_DIR=${SABIOS_DIR} \
	   DD_XDCDIR=${XDC_DIR} \
	   DD_XDC_OPT=DD_XDCDIR=${XDC_DIR} DD_XDCOPTIONS="XDCOPTIONS=v XDCBUILDCFG=${S}/config.bld XDCPATH=${XDCPATH} ${COFFBLD_tidspbridge-ducatisamples}" \
"


inherit ccasefetch

do_compile() {
	oenote "COFF_BLD=${COFFBLD_tidspbridge-ducatisamples}"
	export ${COFFBLD_tidspbridge-ducatisamples}
	chmod -R +w ${S}/*
	${ENV_VAR} oe_runmake -f gmakefile .clean
	${ENV_VAR} oe_runmake -f gmakefile .ducati_bridge 
	oenote "Ducati Bridge compiled!"
	#${ENV_VAR} oe_runmake -f gmakefile .ducati_samples ${COFFBLD_tidspbridge-ducatisamples}
	DEPOT=${DEPOT} oe_runmake -f gmakefile .ducati_samples ${COFFBLD_tidspbridge-ducatisamples}
}
do_stage() {
	install -d ${STAGING_INCDIR}/ducatisamples
	echo ${S} > ${STAGING_INCDIR}/ducatisamples/ducatisamples-source
}

do_deploy() {
        install -d ${DEPLOY_DIR_IMAGE}
	oenote "Deploying Ducati Images and Samples..."
	install ${S}/ipc/ti/sdo/samples/ammu/debug/* ${DEPLOY_DIR_IMAGE}
	install ${S}/ipc/ti/sdo/samples/bios6boot/debug/* ${DEPLOY_DIR_IMAGE}
	install ${S}/ipc/ti/sdo/samples/frameq/debug/* ${DEPLOY_DIR_IMAGE}
        install ${S}/ipc/ti/sdo/samples/heapbuf/debug/* ${DEPLOY_DIR_IMAGE}
        install ${S}/ipc/ti/sdo/samples/listmp/debug/* ${DEPLOY_DIR_IMAGE}
        install ${S}/ipc/ti/sdo/samples/messageq/debug/* ${DEPLOY_DIR_IMAGE}
	install ${S}/ipc/ti/sdo/samples/notify/debug/* ${DEPLOY_DIR_IMAGE}
	install ${S}/ipc/ti/sdo/samples/dmm/debug/* ${DEPLOY_DIR_IMAGE}
	install ${S}/ipc/ti/sdo/samples/rcm/debug/* ${DEPLOY_DIR_IMAGE}
	install ${S}/ipc/ti/sdo/samples/sysmgr/debug/* ${DEPLOY_DIR_IMAGE}
	
}
do_deploy[dirs] = "${S}"
addtask deploy before do_build after do_compile
