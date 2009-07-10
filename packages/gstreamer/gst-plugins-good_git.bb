require gst-plugins-git.inc
DEPENDS += "gst-plugins-base gconf cairo jpeg libpng gtk+ zlib libid3tag flac \
	    speex"
PR = "r0"

EXTRA_OECONF += "--disable-aalib --disable-esd --disable-shout2 --disable-libcaca --without-check \
	--enable-gst_v4l2 --enable-xvideo --enable-experimental"

PACKAGES += "gst-plugin-id3demux"

SRCREV = "122d40a742b18ddaf8873d9c700f87738bd74f38"

# override the SRC_URI from gst-plugins-git.inc to pull from our fork (can be removed when v4l2sink is integrated upstream):
SRC_URI = "git://github.com/roopar/gst-plugins-good.git;protocol=git \
           file://common-20090628.tar.gz \
          "

