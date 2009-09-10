PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage inst2."
LICENSE = "LGPL"
PR = "r0"

inherit ccasefetch

PV = "4.0+cc+${SRCREV}"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/inst2/...  ${SRCREV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/inst2"
CCASE_PATHCOMPONENT = "inst2"
CCASE_PATHCOMPONENTS = "4"

SRC_URI = "file://baseimage-inst2-build-linux.patch;patch=1"

do_compile() {
}

do_stage() {
    chmod -R +w ${S}/*
    install -d ${STAGING_BINDIR}/dspbridge/system/inst2
    cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/system/inst2
}
