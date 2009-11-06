require omap-image-minimal.bb

#IMAGE_INSTALL += "mtd-utils"
IMAGE_INSTALL += "task-omap-bridge"
IMAGE_INSTALL += "task-omap-syslink"
IMAGE_INSTALL += "task-omap-tiler"
#IMAGE_INSTALL += "task-omap-omx"
IMAGE_INSTALL += "task-omap-sn"

IMAGE_ROOTFS_SIZE = "40000"

