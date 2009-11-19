require omap-image-minimal.bb

IMAGE_INSTALL += "task-omap-bridge"
IMAGE_INSTALL += "task-omap-syslink"
IMAGE_INSTALL += "task-omap-tiler"
IMAGE_INSTALL += "task-omap-omx"
IMAGE_INSTALL += "task-omap-sn"
IMAGE_INSTALL += "task-omap-test"
#IMAGE_INSTALL += "task-omap-gst"
#IMAGE_INSTALL += "task-omap-gfx"
#IMAGE_INSTALL += "task-omap-connectivity"
IMAGE_INSTALL += "task-omap-tools"

IMAGE_ROOTFS_SIZE = "65536"

