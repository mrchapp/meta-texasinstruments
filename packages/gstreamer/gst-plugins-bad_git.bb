require gst-plugins-git.inc
DEPENDS += "gst-plugins-base libmusicbrainz tremor libmms amrwb faad2"
PR="r12"

SRC_URI += "\
	file://0001-aacparse-New-implementation.patch;patch=1 \
	file://0002-aacparse-Add-framed-caps-increse-rank.patch;patch=1 \
	"
SRC_URI += "file://eightchan_aacparse_support.patch;patch=1"

EXTRA_OECONF += "--disable-examples --disable-experimental --disable-sdl --disable-cdaudio \
		--with-plugins=flv,musicbrainz,wavpack,ivorbis,amrwbenc,libmms,freeze,rtpmanager,aacparse,qtmux,jpegparse"

ARM_INSTRUCTION_SET = "arm"

SRCREV = "fe312c8643a920f4063ad51c83b0fbb84d85981e"
