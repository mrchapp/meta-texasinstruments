require gst-plugins-git.inc
DEPENDS += "virtual/libx11 alsa-lib freetype gnome-vfs liboil libogg libvorbis libxv"
RDEPENDS += "gnome-vfs-plugin-file gnome-vfs-plugin-http gnome-vfs-plugin-ftp \
             gnome-vfs-plugin-sftp"
PROVIDES_${PN} += "gst-plugins"
PR = "r3"

EXTRA_OECONF += "--disable-freetypetest --disable-pango --disable-theora"

# TODO: regenerate rowstride patch, and go back to pulling from freedesktop.org git repo
SRC_URI = "git://gitorious.org/robclark-gstreamer/gst-plugins-base.git;protocol=git"
SRC_URI += "file://common-20091119.tar.gz"
SRCREV  = "5712fc6c7dbf367a37a963788670e287f8b09892"

