DESCRIPTION = "Texas Instruments DASF Control Task Node Node."
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_DSP/system/dfgm/... LINUX_RLS_${PV}2RC1%\
	      element * /main/LATEST%"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/dfgm"
CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

#set to release or debug
RELEASE = "release"

inherit ccasefetch tisocketnode

SN_DIR=${S}/system/dfgm

do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge/system/dfgm
	cp -a ${S}/system/dfgm/* ${STAGING_BINDIR}/dspbridge/system/dfgm
}
