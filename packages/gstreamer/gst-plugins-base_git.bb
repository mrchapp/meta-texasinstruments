require gst-plugins-git.inc
DEPENDS += "virtual/libx11 alsa-lib freetype gnome-vfs liboil libogg libvorbis libxv"
RDEPENDS += "gnome-vfs-plugin-file gnome-vfs-plugin-http gnome-vfs-plugin-ftp \
             gnome-vfs-plugin-sftp"
PROVIDES_${PN} += "gst-plugins"
PR = "r2"

EXTRA_OECONF += "--disable-freetypetest --disable-pango --disable-theora"

SRC_URI += "file://trace-fix.patch;patch=1"
SRC_URI += "file://rowstride.patch;patch=1"

SRCREV = "906992bdb0674e42a2ce34aec431ff26f92fe135"
