# This is based on Sato image, plus TI stuff
inherit omap-image

IMAGE_FEATURES += "apps-console-core ${SATO_IMAGE_FEATURES}"
IMAGE_FEATURES += "omap-bridge"
IMAGE_FEATURES += "omap-syslink"
IMAGE_FEATURES += "omap-tiler"
IMAGE_FEATURES += "omap-omx"
IMAGE_FEATURES += "omap-sn"
IMAGE_FEATURES += "omap-gst"
IMAGE_FEATURES += "omap-gfx"
#IMAGE_FEATURES += "omap-conn"
IMAGE_FEATURES += "omap-tools"
IMAGE_FEATURES += "omap-test"
#IMAGE_FEATURES += "omap-security"

