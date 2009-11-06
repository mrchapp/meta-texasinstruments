require gst-plugins-git.inc
DEPENDS += "virtual/libx11 alsa-lib freetype gnome-vfs liboil libogg libvorbis libxv"
RDEPENDS += "gnome-vfs-plugin-file gnome-vfs-plugin-http gnome-vfs-plugin-ftp \
             gnome-vfs-plugin-sftp"
PROVIDES_${PN} += "gst-plugins"
PR = "r3"

EXTRA_OECONF += "--disable-freetypetest --disable-pango --disable-theora"

SRC_URI += "file://rowstride.patch;patch=1"

SRCREV = "319baefeba6c293603b8c949fa3861b01d2ecc6b"
