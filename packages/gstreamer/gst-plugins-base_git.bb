require gst-plugins-git.inc
DEPENDS += "virtual/libx11 alsa-lib freetype gnome-vfs liboil libogg libvorbis libxv"
RDEPENDS += "gnome-vfs-plugin-file gnome-vfs-plugin-http gnome-vfs-plugin-ftp \
             gnome-vfs-plugin-sftp"
PROVIDES_${PN} += "gst-plugins"
PR = "r0"

EXTRA_OECONF += "--disable-freetypetest --disable-pango --disable-theora"

SRC_URI += " file://trace-fix.patch;patch=1 "

SRCREV = "da27fd57e8817cf438dbdf3ee793bc85259d223d"

