#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's dspbridge"
PR = "r4"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
    tidspbridge-lib \
    tidspbridge-samples \
    baseimage \
    "
