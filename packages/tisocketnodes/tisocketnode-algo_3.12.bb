DESCRIPTION = "Texas Instruments algo for IPP."
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/algo/... DSP-MM-TII_RLS_${PV}%\
	      element * COMPONENT_ROOT%\
	      element -directory /vobs/wtbu/CSSD_MM_Releases/... /main/LATEST%\
	      "
CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/algo"
CCASE_PATHCOMPONENT = "algo"
CCASE_PATHCOMPONENTS = "3"

inherit ccasefetch

do_stage() {
	chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/algo
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/algo
}