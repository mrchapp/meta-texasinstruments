require gst-plugins-git.inc
DEPENDS += "virtual/libx11 alsa-lib freetype gnome-vfs liboil libogg libvorbis libxv"
RDEPENDS += "gnome-vfs-plugin-file gnome-vfs-plugin-http gnome-vfs-plugin-ftp \
             gnome-vfs-plugin-sftp"
PROVIDES_${PN} += "gst-plugins"
PR = "r4"

EXTRA_OECONF += "--disable-freetypetest --disable-pango --disable-theora"

SRCREV = "3e9648f6f9ecf07a060bab475e42d8e8fa1a5945"
