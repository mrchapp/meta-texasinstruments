#
# Copyright (C) 2009 Texas Instruments
#

DESCRIPTION = "Task for convenient tools that should be included in the root file system"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
	mtd-utils \
	htop \
	powertop \
    bc \
	e2fsprogs e2fsprogs-e2fsck e2fsprogs-mke2fs e2fsprogs-fsck e2fsprogs-tune2fs \
	"
