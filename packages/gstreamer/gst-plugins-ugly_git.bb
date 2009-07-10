require gst-plugins-git.inc
DEPENDS += "gst-plugins-base libid3tag libmad mpeg2dec liba52 lame amrnb"
PR = "r0"

EXTRA_OECONF += "--with-plugins=a52dec,lame,id3tag,mad,mpeg2dec,mpegstream,mpegaudioparse,asfdemux,realmedia,amrnb"

SRCREV = "07fd87b7c6e3aa5a1807d29992a3a591ab90cde9"

