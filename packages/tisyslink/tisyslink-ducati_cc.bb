PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Ducati Samples."
LICENSE = "LGPL"
PR = "r2"
DEPENDS = "titools-cgtarm \
           titools-bios \
           titools-fc \
           titools-xdc "

PV = "0.0+cc+${SRCREV}"

CCASE_SPEC = "%\
          element /vobs/WTSD_MultiCoreSW/Ducati/Bridge/... ${SRCREV}%\
          element /vobs/WTSD_MultiCoreSW/Ducati/Bridge/... -error%\
          element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/WTSD_MultiCoreSW/Ducati/Bridge"
CCASE_PATHCOMPONENT = "Bridge"
CCASE_PATHCOMPONENTS = "3"

FILES_${PN}="/syslink/"

inherit ccasefetch


do_compile() {
    oenote "COFF_BLD=${COFFBLD_tidspbridge-ducatisamples}"
    chmod -R +w ${S}/*
    export ${COFFBLD_tidspbridge-ducatisamples}
    export DEPOT=${STAGING_BINDIR_NATIVE}/titools
    oe_runmake ${PARALLEL_MAKE} -f gmakefile .ducati_samples  
}


do_stage() {
    install -d ${STAGING_BINDIR}/syslink/ducati
    cp -a ${S}/ipc ${STAGING_BINDIR}/syslink/ducati
}


do_install() {
    install -d ${D}/syslink/
    cd ${S}/ipc/ti/sdo/samples
    for xem3 in ammu/debug/* frameq/debug/* heapbuf/debug/* listmp/debug/* messageq/debug/* notify/debug/* dmm/debug/* rcm/debug/* sysmgr/debug/* stress/debug/*; do
        if [ ! -e ${xem3} ]; then
            continue
        fi
        cp ${xem3} ${xem3}.old
        ${STAGING_BINDIR_NATIVE}/titools/cgtarm-*/bin/strip470 ${xem3}
        install -m 0644 ${xem3}      ${D}/syslink/
        mv ${xem3}.old ${xem3}
    done
}

