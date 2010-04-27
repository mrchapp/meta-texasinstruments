SECTION = "libs"
DESCRIPTION = "WLAN firmware"
PR = "r1"

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "omap-4430sdp"

CCASE_SPEC = "%\
	element /vobs/WCGDev/... ${SRCREV}%\
"

CCASE_PATHFETCH = "/vobs/WCGDev/linux/wlan/firmware"
CCASE_PATHCOMPONENTS = 0
CCASE_PATHCOMPONENT = "vobs"

do_stage () {
	install -d ${STAGING_DIR_TARGET}/fw
	install -m 644 ${S}/WCGDev/linux/wlan/firmware/firmware.bin ${STAGING_DIR_TARGET}/fw
}
