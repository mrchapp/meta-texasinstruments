# The distro features change the build options of some components
# across the TI Linux distribution.
#
# The following features shall be set in pokyomap/build/conf/local.conf :
# * gfx          - Include 3D GFX module and libraries.
# * gfx-ticpublt - Include TICPUBLT, a Neon 2D acceleration library
# * tiwifi       - Include support for TI's WLAN stack.
# * tibluetooth  - Include support for TI's Bluetooth and FM stack.
# * gps          - Include support for TI's GPS
# * hdmi         - Include support for HDMI
# * domx         - Include DOMX component
# * 1GHz         - Include 1GHz support
# * ddt          - Device driver tests

# The following are standard Poky DISTRO_FEATURES, not specific to
# OMAP.  Do not touch these unless you know what you are doing.
DISTRO_FEATURES += "alsa bluetooth ext2 irda pcmcia usbgadget usbhost wifi nfs zeroconf pci"

# Default TI OpenMax and Camara Component. Do not remove.
DISTRO_FEATURES += "camera jpegdec jpegenc"

