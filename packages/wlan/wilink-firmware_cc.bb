SECTION = "libs"
DESCRIPTION = "WLAN firmware"
PR = "r0"

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp|omap-3630sdp|zoom2|zoom3"

CCASE_SPEC = "%\
	element /vobs/WCGDev/... ${SRCREV}%\
	"

CCASE_PATHFETCH = "/vobs/WCGDev/linux/wlan/firmware.bin \
	"
CCASE_PATHCOMPONENTS = 0
CCASE_PATHCOMPONENT = "vobs"

PV = "cc+${SRCREV}"

do_stage () {
	install -d ${STAGING_DIR_TARGET}/fw
	install -m 644 ${S}/WCGDev/linux/wlan/firmware.bin ${STAGING_DIR_TARGET}/fw
}
