SECTION = "libs"
DESCRIPTION = "WLAN WAPI feature"
PR = "r0"

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp|omap-3630sdp|zoom2|zoom3"

CCASE_SPEC = "%\
	element /vobs/WCGDev/... ${SRCREV}%\
	"

CCASE_PATHFETCH = "/vobs/WCGDev/linux/wlan/L23WAPI \
	"
CCASE_PATHCOMPONENTS = 3
CCASE_PATHCOMPONENT = "wlan"

PV = "cc+${SRCREV}"

do_stage () {
	install -d ${STAGING_DIR_TARGET}/WAPI
	install -m 644 ${S}/L23WAPI/* ${STAGING_DIR_TARGET}/WAPI/
}
