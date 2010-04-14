SECTION = "libs"
DESCRIPTION = "WLAN firmware"
PR = "r0"

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp|omap-3630sdp|zoom2|zoom3"

CCASE_SPEC = "%\
	element /vobs/WiLink/... ${SRCREV}%\
	"

CCASE_PATHFETCH = "/vobs/WiLink/fw/Latest \
	"
CCASE_PATHCOMPONENTS = 0
CCASE_PATHCOMPONENT = "vobs"

PV = "cc+${SRCREV}"

do_stage () {
	install -d ${STAGING_DIR_TARGET}/fw
	install -m 644 ${S}/WiLink/fw/Latest/Fw1273_CHIP.bin ${STAGING_DIR_TARGET}/fw
	ln -fs ${STAGING_DIR_TARGET}/fw/Fw1273_CHIP.bin ${STAGING_DIR_TARGET}/fw/firmware.bin
}
