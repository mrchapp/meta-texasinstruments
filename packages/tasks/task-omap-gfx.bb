#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for the TI's GFX package"
PR = "r3"

RDEPENDS = "\
	${@base_contains("DISTRO_FEATURES", "gfx", "sgx-kernel-module", "", d)} \
	${@base_contains("DISTRO_FEATURES", "gfx", "sgx-lib-noxws", "", d)} \
	${@base_contains("DISTRO_FEATURES", "gfx", "sgx-tiblt", "", d)} \
	"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"
