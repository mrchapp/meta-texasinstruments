require xorg-driver-video.inc
PE = "1"

DESCRIPTION = "X.Org X server -- fbdev display driver"

#DEPENDS += " "

SRC_URI += "file://0001-Changes-needed-to-detect-enable-multiple-framebuffe.patch;patch=1"

