require gst-plugins-git.inc
DEPENDS += "gst-plugins-base libmusicbrainz tremor libmms amrwb"
PR="r8"

SRC_URI += "file://trace-fix.patch;patch=1"
SRC_URI += "\
	file://0001-jpegparse-new-element.patch;patch=1 \
	file://0002-jpegparse-This-is-a-combination-of-18-commits.patch;patch=1 \
	file://0003-jpegparse-Thumbnail-parsing-support.patch;patch=1 \
	"

EXTRA_OECONF += "--disable-examples --disable-experimental --disable-sdl --disable-cdaudio \
		--with-plugins=flv,musicbrainz,wavpack,ivorbis,amrwbenc,libmms,freeze,rtpmanager,aacparse,qtmux,jpegparse"

ARM_INSTRUCTION_SET = "arm"

SRCREV = "7496a84ea00a5027cc8a5e7eda380f9bdd02c0ef"

# override the SRC_URI from gst-plugins-git.inc to pull from our
# fork (can be removed when jpegparser is integrated upstream):
#SRC_URI = "git://github.com/JJCG/gst-plugins-bad.git;protocol=git;branch=jpegparser3 \
#	   file://common-20090928.tar.gz \
#	   "
