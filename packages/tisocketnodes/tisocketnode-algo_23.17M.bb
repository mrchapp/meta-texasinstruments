DESCRIPTION = "Texas Instruments algo for IPP."
PR = "r0"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/algo"
CCASE_PATHCOMPONENT = "algo"
CCASE_PATHCOMPONENTS = "3"

inherit ccasefetch

do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/algo
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/algo
}
