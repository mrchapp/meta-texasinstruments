require gst-plugins-git.inc
DEPENDS += "gst-plugins-base gconf cairo jpeg libpng gtk+ zlib libid3tag flac \
	    speex"
PR = "r1"

EXTRA_OECONF += "--disable-aalib --disable-esd --disable-shout2 --disable-libcaca --without-check \
	--enable-gst_v4l2 --enable-xvideo --enable-experimental"

PACKAGES += "gst-plugin-id3demux"

SRCREV = "f54398f3c12053aa69ae14e56b941480ba5d88a6"

# override the SRC_URI from gst-plugins-git.inc to pull from our fork (can be removed when v4l2sink is integrated upstream):
SRC_URI = "git://github.com/JJCG/gst-plugins-good.git;branch=jpegparser;protocol=git \
           file://common-20090628.tar.gz \
          "

