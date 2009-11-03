require gst-plugins.inc
DEPENDS += "gst-plugins-base libmusicbrainz tremor amrwb libmms"
PR="r2"

SRC_URI += "file://amrwbparse-seek.patch;patch=1"
SRC_URI += "file://trace-fix.patch;patch=1"

EXTRA_OECONF += "--disable-examples --disable-experimental --disable-sdl --disable-cdaudio \
		--with-plugins=musicbrainz,wavpack,ivorbis,amrwb,libmms,freeze,rtpmanager"

ARM_INSTRUCTION_SET = "arm"

do_configure_prepend() {
	# This m4 file contains nastiness which conflicts with libtool 2.2.2
	rm ${S}/m4/lib-link.m4 || true
}
