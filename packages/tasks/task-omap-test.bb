#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for testing software releases"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
	${@base_contains("DISTRO_FEATURES", "ddt", "device-driver-test", "", d)} \
"
