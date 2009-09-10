PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage make system."
LICENSE = "LGPL"
PR = "r0"

inherit ccasefetch


PV = "4.0+cc+${SRCREV}"


CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/make/... ${SRCREV}%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/make"
CCASE_PATHCOMPONENT = "make"
CCASE_PATHCOMPONENTS = "3"


do_compile() {
}

do_stage() {
    chmod -R +w ${S}/*
    install -d ${STAGING_BINDIR}/dspbridge/make
    cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/make
}
