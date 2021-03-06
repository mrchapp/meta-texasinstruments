DESCRIPTION = "Texas Instruments MPU/DSP Bridge Socket Node compilation."
LICENSE = "LGPL"
PR = "r0"

DEPENDS = " \
    titools-bios \
    titools-cgt6x \
    titools-fc \
    titools-xdc \
    titools-ipc \
    tidspbridge-dllcreate"

PACKAGES = "${PN}-dev ${PN}-dbg ${PN}"
#FILES_${PN}-dev = "/dspbridge/exports/lib/lib*.so"
#FILES_${PN}-dbg += "/dspbridge/exports/lib/.debug"
FILES_${PN}-dbg += "/dspbridge/.debug"
FILES_${PN} = "/dspbridge"

inherit ccasefetch

PV = "0.0+cc+${SRCREV}"

CCASE_SPEC = "%\
	      element /vobs/WTSD_MultiCoreSW/Tesla/Bridge/... ${SRCREV}%\
	      element /vobs/WTSD_MultiCoreSW/Tesla/Bridge/... -error%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/WTSD_MultiCoreSW/Tesla/Bridge"
CCASE_PATHCOMPONENT = "Bridge"
CCASE_PATHCOMPONENTS = "3"

ENV_VAR = " \
        DEPOT=${STAGING_BINDIR_NATIVE}/titools \
        DLLCREATE_DIR=${STAGING_BINDIR_NATIVE}/DLLcreate \
        FC_VER=3_00_00_50_eng \
	DSP_JOBS="${PARALLEL_MAKE}" \
        "       

SRC_URI = "file://tidspbridge.patch;patch=1"


do_compile() {
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
	install -m 0644 ${S}/ti/dspbridge/dsp/samples/*.dof64T ${D}/dspbridge
	install -m 0644 ${S}/ti/dspbridge/dsp/samples/*.dll64T ${D}/dspbridge
}

