require xserver-xf86-lite.inc

PR = "r1"

SRC_URI += "file://dolt-fix.patch;patch=1 \
            file://0001-Changes-needed-for-multi-framebuffer-devices.patch;patch=1"


