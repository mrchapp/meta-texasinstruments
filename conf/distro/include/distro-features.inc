# These distro features change the build options of some components
# across the TI Linux distribution.
#
# Possible features are:
# * 720p	- Enable 720p OpenMAX components, kernel.
# * spark	- Enable Sorenson spark video decoder.
# * gfx		- Include 3D GFX module and libraries.
# * tiwifi	- Include support for TI's WLAN stack.
# * tibluetooth	- Include support for TI's Bluetooth and FM stack.
# * gps		- Include support for TI's GPS
# * testpatterns - Include OMX test patterns

# The following are standard Poky DISTRO_FEATURES, not specific to
# OMAP.  Do not touch these unless you know what you are doing.

DISTRO_FEATURES += "alsa bluetooth ext2 irda pcmcia usbgadget usbhost wifi nfs zeroconf pci"

# Below are OMAP-specific DISTRO_FEATURES.  Hack away!
# This is a multimedia-based distribution. We can specify what
# codecs to enable in this release.

# Default TI OpenMax and Camara Component. Do not remove.
DISTRO_FEATURES += "camera jpegdec jpegenc"

# Enable Sorenson spark video decoder
DISTRO_FEATURES += "spark"

# Enable 3D GFX module and libraries
#DISTRO_FEATURES += "gfx"

# Enable 3D GFX Benchmark application delivery - requires gfx
#DISTRO_FEATURES += "gfx-bp"

# Enable 720p OpenMAX components - Ittiam
DISTRO_FEATURES += "720p"

# Enable 720p OpenMAX components - Aricent - deprecated
#DISTRO_FEATURES += "Aricent_720p"

# Enable support for TI Bluetooth and FM stack
DISTRO_FEATURES += "tibluetooth"

# Enable support for TI's WLAN on zoom3 - no impact on other boards.
DISTRO_FEATURES += "tiwifi"

# Enable support for WAPI in WLAN on zoom3 - no impact on other boards.
DISTRO_FEATURES += "wapi"

# Enable support for TI-BT test framework on zoom3 - no impact on other boards.
#DISTRO_FEATURES += "tibt-test-fw"
