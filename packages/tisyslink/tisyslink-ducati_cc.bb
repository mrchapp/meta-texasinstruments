PRIORITY = "optional"
DESCRIPTION = "Texas Instruments DSP Ducati Samples."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "titools-cgtarm \
	   titools-bios \
           titools-fc \
           titools-xdc "

PV = "1.0+cc+${SRCREV}"

CCASE_SPEC = "%\
	      element /vobs/WTSD_MultiCoreSW/Ducati/Bridge/... ${SRCREV}%\
	      element /vobs/WTSD_MultiCoreSW/Ducati/Bridge/... -error%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/WTSD_MultiCoreSW/Ducati/Bridge"
CCASE_PATHCOMPONENT = "Bridge"
CCASE_PATHCOMPONENTS = "3"


inherit ccasefetch


do_compile() {
    oenote "COFF_BLD=${COFFBLD_tidspbridge-ducatisamples}"
    chmod -R +w ${S}/*
    export ${COFFBLD_tidspbridge-ducatisamples}
    export DEPOT=${STAGING_BINDIR}/titools
    export XDCARGS='${PARALLEL_MAKE}'
    oe_runmake -f gmakefile .ducati_samples  
}


do_stage() {
    install -d ${STAGING_BINDIR}/syslink/ducati
    cp -a ${S}/ipc ${STAGING_BINDIR}/syslink/ducati
}


do_install() {
    install -d ${D}/syslink/ducati
    cd ${S}/ipc/ti/sdo/samples
    install -m 0644 ammu/debug/*      ${D}/syslink/ducati
#    install bios6boot/debug/* ${D}/syslink/ducati
    install -m 0644 frameq/debug/*    ${D}/syslink/ducati
    install -m 0644 heapbuf/debug/*   ${D}/syslink/ducati
    install -m 0644 listmp/debug/*    ${D}/syslink/ducati
    install -m 0644 messageq/debug/*  ${D}/syslink/ducati
    install -m 0644 notify/debug/*    ${D}/syslink/ducati
    install -m 0644 dmm/debug/*       ${D}/syslink/ducati
    install -m 0644 rcm/debug/*       ${D}/syslink/ducati
    install -m 0644 sysmgr/debug/*    ${D}/syslink/ducati
}

FILES_${PN}="/syslink/ducati"
