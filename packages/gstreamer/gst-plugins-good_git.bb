require gst-plugins-git.inc
DEPENDS += "gst-plugins-base gconf cairo jpeg libpng gtk+ zlib libid3tag flac \
	    speex"
PR = "r7"

EXTRA_OECONF += "--disable-aalib --disable-esd --disable-shout2 --disable-libcaca --without-check \
	--enable-gst_v4l2 --enable-xvideo --enable-experimental"

PACKAGES += "gst-plugin-id3demux"

SRCREV = "41a0eb10445624f3b5dcb8be326fc22ad7d4d21e"

