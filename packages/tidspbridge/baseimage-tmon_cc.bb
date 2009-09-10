PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge Socket Node compilation."
LICENSE = "LGPL"
PR = "r0"

inherit ccasefetch

PV = "4.0+cc+${SRCREV}"


CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/tmon/... ${SRCREV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/tmon"
CCASE_PATHCOMPONENT = "tmon"
CCASE_PATHCOMPONENTS = "4"

SRC_URI = "file://baseimage-tmon-build-linux.patch;patch=1"

do_compile() {
}

do_stage() {
    chmod -R +w ${S}/*
    install -d ${STAGING_BINDIR}/dspbridge/system/tmon
    cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/system/tmon
}
