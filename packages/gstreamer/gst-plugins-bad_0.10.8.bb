require gst-plugins.inc
DEPENDS += "gst-plugins-base libmusicbrainz tremor amrwb libmms"
PR="r2"

SRC_URI += "file://amrwbparse-seek.patch;patch=1"
SRC_URI += "file://trace-fix.patch;patch=1"

EXTRA_OECONF += "--disable-examples --disable-experimental --disable-sdl --disable-cdaudio \
		--with-plugins=musicbrainz,wavpack,ivorbis,amrwb,libmms,freeze,rtpmanager"

ARM_INSTRUCTION_SET = "arm"

