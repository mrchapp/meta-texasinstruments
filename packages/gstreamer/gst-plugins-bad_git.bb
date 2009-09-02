require gst-plugins-git.inc
DEPENDS += "gst-plugins-base libmusicbrainz tremor amrwb libmms"
PR="r4"

SRC_URI += "file://trace-fix.patch;patch=1"

EXTRA_OECONF += "--disable-examples --disable-experimental --disable-sdl --disable-cdaudio \
		--with-plugins=flv,musicbrainz,wavpack,ivorbis,amrwb,libmms,freeze,rtpmanager,aacparse"

ARM_INSTRUCTION_SET = "arm"

SRCREV = "44f0d31ba385ee644a8bd64efc64679c2e92e7eb"

