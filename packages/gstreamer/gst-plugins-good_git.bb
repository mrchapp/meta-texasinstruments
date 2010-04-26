require gst-plugins-git.inc
DEPENDS += "gst-plugins-base gconf cairo jpeg libpng gtk+ zlib libid3tag flac \
	    speex"
PR = "r9"

EXTRA_OECONF += "--disable-aalib --disable-esd --disable-shout2 --disable-libcaca --without-check \
	--enable-gst_v4l2 --enable-xvideo --enable-experimental"

PACKAGES += "gst-plugin-id3demux"

SRC_URI += "git://dev.omapzoom.org/pub/scm/gstreamer/gst-plugins-good.git;protocol=git"
SRC_URI += "file://remove-Wmissingdirs.patch;patch=1"
