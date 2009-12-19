require gst-plugins-git.inc
DEPENDS += "gst-plugins-base libid3tag libmad mpeg2dec liba52 lame opencore-amr"
PR = "r3"

EXTRA_OECONF += "--with-plugins=a52dec,lame,id3tag,mad,mpeg2dec,mpegstream,mpegaudioparse,asfdemux,realmedia,amrnb,amrwbdec"

SRCREV = "ea7a9e550ad87ad967ffcd7538afc76f6f330877"
