require gst-plugins-git.inc
DEPENDS += "gst-plugins-base gconf cairo jpeg libpng gtk+ zlib libid3tag flac \
	    speex"
PR = "r6"

EXTRA_OECONF += "--disable-aalib --disable-esd --disable-shout2 --disable-libcaca --without-check \
	--enable-gst_v4l2 --enable-xvideo --enable-experimental"

PACKAGES += "gst-plugin-id3demux"

SRCREV = "188725811f00a8eb6293c3810418016728b512bf"

SRC_URI += "\
	file://v4l2sink-update.patch;patch=1 \
	file://rowstride.patch;patch=1 \
	"
