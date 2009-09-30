require gst-plugins-git.inc
DEPENDS += "gst-plugins-base libid3tag libmad mpeg2dec liba52 lame amrnb"
PR = "r1"

EXTRA_OECONF += "--with-plugins=a52dec,lame,id3tag,mad,mpeg2dec,mpegstream,mpegaudioparse,asfdemux,realmedia,amrnb"

SRCREV = "820abb3ab8a8d6de01b5ec03d3e54333342198ba"
