# These distro features change the build options of some components
# across the TI Linux distribution.
#
# Possible features are:
# * 720p	- Enable 720p OpenMAX components, kernel.
# * gfx		- Include 3D GFX module and libraries.
# * tiwifi	- Include support for TI's WLAN stack.
# * tibluetooth	- Include support for TI's Bluetooth and FM stack.
# * gps		- Include support for TI's GPS
# * rarv        - Enable readaudio/realvideo codecs
# * testpatterns - Include OMX test patterns

# The following are standard Poky DISTRO_FEATURES, not specific to
# OMAP.  Do not touch these unless you know what you are doing.

DISTRO_FEATURES += "alsa bluetooth ext2 irda pcmcia usbgadget usbhost wifi nfs zeroconf pci"

# Below are OMAP-specific DISTRO_FEATURES.  Hack away!
# This is a multimedia-based distribution. We can specify what
# codecs to enable in this release.

DISTRO_FEATURES += "gfx 720p rarv tibluetooth tiwifi gps"

### Codecs
DISTRO_FEATURES += "videodec videoenc"
DISTRO_FEATURES += "postproc"
DISTRO_FEATURES += "prepostproc"
DISTRO_FEATURES += "camera"
#
DISTRO_FEATURES += "aacdec aacenc"
DISTRO_FEATURES += "g711dec g711enc g722dec g722enc g723dec g723enc g726dec g726enc g729dec g729enc"
DISTRO_FEATURES += "gsmfrdec gsmfrenc gsmhrdec gsmhrenc"
DISTRO_FEATURES += "ilbcdec ilbcenc"
DISTRO_FEATURES += "mp3dec"
DISTRO_FEATURES += "nbamrdec nbamrenc"
DISTRO_FEATURES += "pcmdec pcmenc"
DISTRO_FEATURES += "wbamrdec wbamrenc"
DISTRO_FEATURES += "wmadec"
#
DISTRO_FEATURES += "jpegdec jpegenc"
