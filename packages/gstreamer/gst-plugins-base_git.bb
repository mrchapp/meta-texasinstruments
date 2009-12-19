require gst-plugins-git.inc
DEPENDS += "virtual/libx11 alsa-lib freetype gnome-vfs liboil libogg libvorbis libxv"
RDEPENDS += "gnome-vfs-plugin-file gnome-vfs-plugin-http gnome-vfs-plugin-ftp \
             gnome-vfs-plugin-sftp"
PROVIDES_${PN} += "gst-plugins"
PR = "r4"

EXTRA_OECONF += "--disable-freetypetest --disable-pango --disable-theora"

SRCREV = "8b4f6dd43bf13d286f11f532033ac46aec33b13b"
SRC_URI += "file://rowstride.patch;patch=1"
