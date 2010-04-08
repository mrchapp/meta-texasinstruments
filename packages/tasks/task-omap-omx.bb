#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's OpenMAX IL"
PR = "r8"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
#    tiopenmax-audiodecode \
#    tiopenmax-audioencode \
#    tiopenmax-base \
#    tiopenmax-lcml \
    tiopenmax-alsa \
    tiopenmax-core \
    tiopenmax-osal \
    ${@base_contains("DISTRO_FEATURES", "domx", "tiopenmax-domx", "", d)} \
"


