PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage dasf."
LICENSE = "LGPL"
PR = "r0"

inherit ccasefetch

PV = "4.0+cc+${SRCREV}"


CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/dasf/... ${SRCREV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/dasf"
CCASE_PATHCOMPONENT = "dasf"
CCASE_PATHCOMPONENTS = "4"

SRC_URI = "file://baseimage-dasf-build-linux.patch;patch=1"


do_compile() {
}

do_stage() {
    chmod -R +w ${S}/*
    install -d ${STAGING_BINDIR}/dspbridge/system/dasf
    cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/system/dasf
}
