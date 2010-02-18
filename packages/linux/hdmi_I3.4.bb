PRIORITY = "optional"
DESCRIPTION = "HDMI kernel patch provider"
LICENSE = "GPL v2"
PR = "r0"

COMPATIBLE_MACHINE = "zoom3"
PACKAGE_ARCH = "${MACHINE_ARCH}"

CCASE_SPEC = "%\
	      element /vobs/wtbu/CSSD_Linux_Releases/... HDMI_REL_L23.${PV}%\
	      element * /main/0%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_Linux_Releases/3430/Linux_23.I3.x/HDMI-kernel.patch"
CCASE_PATHCOMPONENT = "Linux_23.I3.x"
CCASE_PATHCOMPONENTS = "4"


inherit ccasefetch

do_stage() {
    install -d ${STAGING_KERNEL_DIR}/hdmi
    install -m 0644 ${S}/HDMI-kernel.patch ${STAGING_KERNEL_DIR}/hdmi
}
