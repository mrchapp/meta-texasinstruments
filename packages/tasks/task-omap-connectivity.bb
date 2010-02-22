#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's connectivity package"
PR = "r2"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
	${@base_contains("DISTRO_FEATURES", "tibluetooth", "btfm", "", d)} \
	${@base_contains("DISTRO_FEATURES", "tibluetooth", "kfmapp", "", d)} \
	${@base_contains("DISTRO_FEATURES", "bluetooth", "bluez-gnome", "", d)} \
	${@base_contains("DISTRO_FEATURES", "bluetooth", "obex-data-server", "", d)} \
	${@base_contains("DISTRO_FEATURES", "bluetooth", "gnome-bluetooth", "", d)} \
"

RDEPENDS_zoom3 = "\
	${@base_contains("DISTRO_FEATURES", "tibluetooth", "btfm", "", d)} \
	${@base_contains("DISTRO_FEATURES", "tibluetooth", "kfmapp", "", d)} \
	${@base_contains("DISTRO_FEATURES", "bluetooth", "bluez-gnome", "", d)} \
	${@base_contains("DISTRO_FEATURES", "bluetooth", "obex-data-server", "", d)} \
	${@base_contains("DISTRO_FEATURES", "bluetooth", "gnome-bluetooth", "", d)} \
	${@base_contains("DISTRO_FEATURES", "tibt-test-fw", "fuse", "", d)} \
	${@base_contains("DISTRO_FEATURES", "tibt-test-fw", "fuse-utils", "", d)} \
	${@base_contains("DISTRO_FEATURES", "tibt-test-fw", "obexfs", "", d)} \	
	${@base_contains("DISTRO_FEATURES", "tiwifi", "wilink", "", d)} \
	${@base_contains("DISTRO_FEATURES", "tiwifi", "iperf", "", d)} \
"
