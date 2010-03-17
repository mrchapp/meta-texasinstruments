require gst-plugins-git.inc
DEPENDS += "virtual/libx11 alsa-lib freetype gnome-vfs liboil libogg libvorbis libxv"
RDEPENDS += "gnome-vfs-plugin-file gnome-vfs-plugin-http gnome-vfs-plugin-ftp \
             gnome-vfs-plugin-sftp"
PROVIDES_${PN} += "gst-plugins"
PR = "r5"

EXTRA_OECONF += "--disable-freetypetest --disable-pango --disable-theora"

SRC_URI += "git://dev.omapzoom.org/pub/scm/gstreamer/gst-plugins-base.git;protocol=git"
