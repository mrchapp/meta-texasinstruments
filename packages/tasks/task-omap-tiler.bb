#
# Copyright (C) 2009 Texas Instruments
#

DESCRIPTION = "Tasks for TI's Tiler"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
    titiler-memmgr\
    titiler-d2cmap\
   "

