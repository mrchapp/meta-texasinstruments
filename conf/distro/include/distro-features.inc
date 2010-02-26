# The distro features change the build options of some components
# across the TI Linux distribution.
#

# The following are standard Poky DISTRO_FEATURES, not specific to
# OMAP.  Do not touch these unless you know what you are doing.
DISTRO_FEATURES += "alsa ext2 irda pcmcia usbgadget usbhost wifi nfs zeroconf pci"

# Default TI OpenMax and Camara Component. Do not remove.
DISTRO_FEATURES += "camera jpegdec jpegenc"

