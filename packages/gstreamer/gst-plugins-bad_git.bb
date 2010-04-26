require gst-plugins-git.inc
DEPENDS += "gst-plugins-base libmusicbrainz tremor libmms amrwb faad2"
PR="r15"

SRC_URI += "git://dev.omapzoom.org/pub/scm/gstreamer/gst-plugins-bad.git;protocol=git"

EXTRA_OECONF += "--disable-examples --disable-experimental --disable-sdl --disable-cdaudio \
		--with-plugins=flv,musicbrainz,wavpack,ivorbis,amrwbenc,libmms,freeze,rtpmanager,aacparse,qtmux,jpegparse"

ARM_INSTRUCTION_SET = "arm"

SRC_URI += "file://remove-Wmissingdirs.patch;patch=1"
