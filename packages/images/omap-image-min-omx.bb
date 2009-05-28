#
# Copyright (C) 2007 OpenedHand Ltd.
#
IMAGE_INSTALL = "\
	${ROOTFS_PKGMANAGE} \
	task-poky-boot \
	task-omap-bridge \
	${@base_contains("DISTRO", "tilinux-os", "", "task-omap-sn", d)} \
	task-omap-omx \
	"

IMAGE_LINGUAS = " "

inherit poky-image

# remove not needed ipkg informations
ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files"
