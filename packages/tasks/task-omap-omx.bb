#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's OpenMAX IL"
PR = "r6"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
   	tiopenmax-audiodecode \
	tiopenmax-audioencode \
    tiopenmax-base \
    tiopenmax-core \
    tiopenmax-lcml \
    tiopenmax-osal \
    ${@base_contains("DISTRO_FEATURES", "domx", "tiopenmax-domx", "", d)} \
"


