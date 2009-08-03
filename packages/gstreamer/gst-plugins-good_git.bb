require gst-plugins-git.inc
DEPENDS += "gst-plugins-base gconf cairo jpeg libpng gtk+ zlib libid3tag flac \
	    speex"
PR = "r0"

EXTRA_OECONF += "--disable-aalib --disable-esd --disable-shout2 --disable-libcaca --without-check \
	--enable-gst_v4l2 --enable-xvideo --enable-experimental"

PACKAGES += "gst-plugin-id3demux"

SRCREV = "6035585c21076f1635787f653b4224faf7894c91"

# override the SRC_URI from gst-plugins-git.inc to pull from our fork (can be removed when v4l2sink is integrated upstream):
SRC_URI = "git://github.com/roopar/gst-plugins-good.git;protocol=git \
           file://common-20090628.tar.gz \
          "

