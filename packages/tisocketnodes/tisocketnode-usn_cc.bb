PRIORITY = "optional"
DESCRIPTION = "Texas Instruments USN Socket Node."
LICENSE = "LGPL"
PR = "r3"

inherit tisocketnode

PV = "4.0+cc+${SRCREV}"

CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_DSP/... ${SRCREV}%\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/system/usn"
CCASE_PATHCOMPONENT = "wtbu"
CCASE_PATHCOMPONENTS = "1"


SN_DIR = "${S}/OMAPSW_DSP/system/usn"

XDCARGS = "profile=sn_debug"

do_stage() {
    install -d ${STAGING_BINDIR}/dspbridge
    cp -a ${S}/* ${STAGING_BINDIR}/dspbridge
}


