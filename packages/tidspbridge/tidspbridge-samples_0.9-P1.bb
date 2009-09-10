PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP Tesla Samples."
LICENSE = "LGPL"
PR = "r2"
DEPENDS = " \
	   tidspbridge-tiler \
	   tidspbridge-bios \
	   tidspbridge-cgt7x \
           tidspbridge-fc \
           tidspbridge-xdc \
	   tidspbridge-ipc \
	   tidspbridge-dllcreate \
	"

PACKAGES = "${PN}-dev ${PN}-dbg ${PN}"
#FILES_${PN}-dev = "/dspbridge/exports/lib/lib*.so"

#FILES_${PN}-dbg += "/dspbridge/exports/lib/.debug"
FILES_${PN}-dbg += "/dspbridge/.debug"
FILES_${PN} = "/dspbridge"

CCASE_SPEC = "%\
	      element /vobs/WTSD_MultiCoreSW/Tesla/Bridge/... BRIDGE-DSP_RLS_${PV}%\
	      element /vobs/WTSD_MultiCoreSW/Tesla/Bridge/... -error%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/WTSD_MultiCoreSW/Tesla/Bridge"
CCASE_PATHCOMPONENT = "Bridge"
CCASE_PATHCOMPONENTS = "3"


BIOS_VER = "6_20_02_42_eng"
XDC_VER = "3_15_03_64_eng"
FC_VER = "3_00_00_44_eng"
IPC_VER = "1_00_03_52_eng"
CGT_VER = "7.0.0B1"

DEPOT = ${STAGING_BINDIR}/dspbridge/tools
SABIOS_DIR =${DEPOT}/bios_${BIOS_VER}/packages
DD_XDCDIR = ${DEPOT}/xdctools_${XDC_VER}

ENV_VAR = "DEPOT=${DEPOT} \
	   SABIOS_DIR=${SABIOS_DIR} \
	   BIOS_OPTS=SABIOS_DIR=${SABIOS_DIR} \
	   DD_XDCDIR=${DD_XDCDIR} \
	   DD_XDC_OPT=DD_XDCDIR=${DD_XDCDIR} DD_XDCOPTIONS="XDCOPTIONS=v XDCBUILDCFG=${S}/private.bld XDCPATH=${SABIOS_DIR}\;${DEPOT}/framework_components_${FC_VER}/fctools/packages\;${DEPOT}/framework_components_${FC_VER}/packages\;${S}/bdsptools/packages\;${S}\;${DEPOT}/ipc_${IPC_VER}/packages XDCTARGETS=C64T" \
	   DLLCREATE_DIR=${STAGING_BINDIR_NATIVE}/DLLcreate \
"

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
	${ENV_VAR} oe_runmake -f gmakefile .bridge_samples 
}

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/dsp
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/dsp
}

do_install() {
	install -d ${D}/dspbridge
	cd ${S}/ti/dspbridge/dsp/samples
	install -m 0644 *.dof* ${D}/dspbridge
	install -m 0644 *.dll64T* ${D}/dspbridge

	if [ `find ${STAGING_LIBDIR}/modules -name bridgedriver.ko` != "" ]
	then
		oenote "Installing bridgedriver.ko"
		install -m 0644 `find ${STAGING_LIBDIR}/modules -name bridgedriver.ko` ${D}/dspbridge
	fi
	if [ `find ${STAGING_LIBDIR}/modules -name dspbridge.ko` != "" ]
	then
		oenote "Installing dspbridge.ko"
		install -m 0644 `find ${STAGING_LIBDIR}/modules -name dspbridge.ko` ${D}/dspbridge
	fi
	if [ `find ${STAGING_LIBDIR}/modules -name omap_notify.ko` != "" ]
	then
		oenote "Installing omap_notify.ko"
		install -m 0644 `find ${STAGING_LIBDIR}/modules -name omap_notify.ko` ${D}/dspbridge
	fi
	if [ `find ${STAGING_LIBDIR}/modules -name notify_disp.ko` != "" ]
	then
		oenote "Installing notify_disp.ko"
		install -m 0644 `find ${STAGING_LIBDIR}/modules -name notify_disp.ko` ${D}/dspbridge
	fi
	if [ `find ${STAGING_LIBDIR}/modules -name notify_tesladriver.ko` != "" ]
	then
		oenote "Installing notify_tesladriver.ko"
		install -m 0644 `find ${STAGING_LIBDIR}/modules -name notify_tesladriver.ko` ${D}/dspbridge
	fi
	if [ `find ${STAGING_LIBDIR}/modules -name notify_ducatidriver.ko` != "" ]
	then
		oenote "Installing notify_ducatidriver.ko"
		install -m 0644 `find ${STAGING_LIBDIR}/modules -name notify_ducatidriver.ko` ${D}/dspbridge
	fi
	if [ `find ${STAGING_LIBDIR}/modules -name syslink_proc4430.ko` != "" ]
	then
		oenote "Installing syslink_proc4430.ko"
		install -m 0644 `find ${STAGING_LIBDIR}/modules -name syslink_proc4430.ko` ${D}/dspbridge
	fi
	if [ `find ${STAGING_LIBDIR}/modules -name syslink_proc.ko` != "" ]
	then
		oenote "Installing syslink_proc.ko"
		install -m 0644 `find ${STAGING_LIBDIR}/modules -name syslink_proc.ko` ${D}/dspbridge
	fi
	if [ `find ${STAGING_LIBDIR}/modules -name syslink_ipc.ko` != "" ]
	then
		oenote "Installing syslink_ipc.ko"
		install -m 0644 `find ${STAGING_LIBDIR}/modules -name syslink_ipc.ko` ${D}/dspbridge
        fi

}
