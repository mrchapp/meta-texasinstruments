require xserver-xf86-lite.inc

PR = "r0"

SRC_URI += "file://dolt-fix-1.7.0.patch;patch=1 \
            file://randr-support-1.7.0.patch;patch=1"
