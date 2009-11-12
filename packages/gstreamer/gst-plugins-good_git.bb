require gst-plugins-git.inc
DEPENDS += "gst-plugins-base gconf cairo jpeg libpng gtk+ zlib libid3tag flac \
	    speex"
PR = "r5"

EXTRA_OECONF += "--disable-aalib --disable-esd --disable-shout2 --disable-libcaca --without-check \
	--enable-gst_v4l2 --enable-xvideo --enable-experimental"

PACKAGES += "gst-plugin-id3demux"

SRCREV = "cc47277e6587be79a3bb1111a457b04c01d37cd8"

# override the SRC_URI from gst-plugins-git.inc to pull from our
# fork (can be removed when v4l2sink is integrated upstream):
SRC_URI = "git://gitorious.org/robclark-gstreamer/gst-plugins-good.git;protocol=git \
           file://common-20090928.tar.gz \
           file://rowstride.patch;patch=1 \
          "
