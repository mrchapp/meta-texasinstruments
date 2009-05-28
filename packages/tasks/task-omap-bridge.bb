#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's dspbridge"
PR = "r3"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
    virtual/dspbridge-module \
    tidspbridge-lib \
    tidspbridge-extra \
    ${@base_contains("DISTRO_FEATURES", "opensource", "", "tidspbridge-samples", d)} \
    ${@base_contains("DISTRO_FEATURES", "opensource", "", "baseimage", d)} \
    "
