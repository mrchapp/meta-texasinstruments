require gst-plugins-git.inc
DEPENDS += "virtual/libx11 alsa-lib freetype gnome-vfs liboil libogg libvorbis libxv"
RDEPENDS += "gnome-vfs-plugin-file gnome-vfs-plugin-http gnome-vfs-plugin-ftp \
             gnome-vfs-plugin-sftp"
PROVIDES_${PN} += "gst-plugins"
PR = "r1"

EXTRA_OECONF += "--disable-freetypetest --disable-pango --disable-theora"

# override the SRC_URI from gst-plugins-git.inc to pull from our fork (can be removed when rowstride is integrated upstream):
SRC_URI = "git://github.com/robclark/gst-plugins-base.git;protocol=git \
	file://common-20090628.tar.gz \
	"

SRC_URI += " file://trace-fix.patch;patch=1 "

SRCREV = "77e43fb0fc58e940323aff15ce5b8a688d9ae171"

