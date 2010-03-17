require gst-plugins-git.inc
DEPENDS += "gst-plugins-base libid3tag libmad mpeg2dec liba52 lame opencore-amr"
PR = "r4"

EXTRA_OECONF += "--with-plugins=a52dec,lame,id3tag,mad,mpeg2dec,mpegstream,mpegaudioparse,asfdemux,realmedia,amrnb,amrwbdec"

SRC_URI += "git://dev.omapzoom.org/pub/scm/gstreamer/gst-plugins-ugly.git;protocol=git"
