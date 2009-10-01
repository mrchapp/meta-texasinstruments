require gst-plugins-git.inc
DEPENDS += "gst-plugins-base libmusicbrainz tremor libmms opencore-amr"
PR="r6"

SRC_URI += "file://trace-fix.patch;patch=1"

EXTRA_OECONF += "--disable-examples --disable-experimental --disable-sdl --disable-cdaudio \
		--with-plugins=flv,musicbrainz,wavpack,ivorbis,amrwbenc,libmms,freeze,rtpmanager,aacparse,qtmux,jpegparse"

ARM_INSTRUCTION_SET = "arm"

SRCREV = "a5e53bd26d8e48d1a2e62c700fa51df917459581"

# override the SRC_URI from gst-plugins-git.inc to pull from our
# fork (can be removed when jpegparser is integrated upstream):
SRC_URI = "git://github.com/JJCG/gst-plugins-bad.git;protocol=git;branch=jpegparser3 \
	   file://common-20090928.tar.gz \
	   "
