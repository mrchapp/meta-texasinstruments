#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's syslink"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
    tisyslink-lib\
    tisyslink-ducati\
    tisyslink-tiler\
   "

#    tisyslink-ducati-mm \

