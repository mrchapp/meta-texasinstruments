#
# Copyright (C) 2009 Texas Instruments
#

DESCRIPTION = "Task for convenient tools that should be included in the root file system"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
	mtd-utils \
	htop \
	powertop \
	"
