PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP Ducati Samples."
LICENSE = "LGPL"
PR = "r1"
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

DEPOT = ${STAGING_BINDIR}/dspbridge/tools
SABIOS_DIR = ${STAGING_BINDIR}/dspbridge/tools/bios_6_20_00_30/packages
XDC_DIR = ${STAGING_BINDIR}/dspbridge/tools/xdctools_3_15_00_39
FC_DIR = ${STAGING_BINDIR}/dspbridge/tools/framework_components_3_00_00_36
CGTARM_DIR = ${STAGING_BINDIR}/dspbridge/tools/cgtarm-4.6.0

ENV_VAR = "DEPOT=${DEPOT} \
	   SABIOS_DIR=${SABIOS_DIR} \
	   BIOS_OPTS=SABIOS_DIR=${SABIOS_DIR} \
	   DD_XDCDIR=${XDC_DIR} \
	   DD_XDC_OPT=DD_XDCDIR=${XDC_DIR} DD_XDCOPTIONS="XDCOPTIONS=v XDCBUILDCFG=${S}/config.bld XDCPATH=${SABIOS_DIR}\;${FC_DIR}/fctools/packages\;${FC_DIR}/packages\;${S}/bdsptools/packages\;${CGTARM_DIR}/packages\;${S} ${COFFBLD_tidspbridge-ducatisamples}" \
"


inherit ccasefetch

do_compile() {
	oenote "COFF_BLD=${COFFBLD_tidspbridge-ducatisamples}"
	export ${COFFBLD_tidspbridge-ducatisamples}
	chmod -R +w ${S}/*
	${ENV_VAR} oe_runmake -f gmakefile .clean
	${ENV_VAR} oe_runmake -f gmakefile .ducati_bridge 
	oenote "Ducati Bridge compiled!"
	${ENV_VAR} oe_runmake -f gmakefile .ducati_samples ${COFFBLD_tidspbridge-ducatisamples}
}

do_deploy() {
        install -d ${DEPLOY_DIR_IMAGE}
	oenote "Deploying Ducati Images and Samples..."
	install ${S}/ipc/ti/sdo/ipc/samples/ammu/debug/* ${DEPLOY_DIR_IMAGE}
	install ${S}/ipc/ti/sdo/ipc/samples/bios6boot/debug/* ${DEPLOY_DIR_IMAGE}
	install ${S}/ipc/ti/sdo/ipc/samples/frameq/debug/* ${DEPLOY_DIR_IMAGE}
        install ${S}/ipc/ti/sdo/ipc/samples/heapbuf/debug/* ${DEPLOY_DIR_IMAGE}
        install ${S}/ipc/ti/sdo/ipc/samples/listmp/debug/* ${DEPLOY_DIR_IMAGE}
        install ${S}/ipc/ti/sdo/ipc/samples/messageq/debug/* ${DEPLOY_DIR_IMAGE}
	install ${S}/ipc/ti/sdo/ipc/samples/notify/debug/* ${DEPLOY_DIR_IMAGE}
	install ${S}/ipc/ti/sdo/ipc/samples/dmm/debug/* ${DEPLOY_DIR_IMAGE}
	install ${S}/ipc/ti/sdo/ipc/samples/rcm/debug/* ${DEPLOY_DIR_IMAGE}
	
}
do_deploy[dirs] = "${S}"
addtask deploy before do_build after do_compile
