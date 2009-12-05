require gst-plugins-git.inc
DEPENDS += "gst-plugins-base libmusicbrainz tremor libmms amrwb faad2"
PR="r11"

SRC_URI += "file://trace-fix.patch;patch=1"
SRC_URI += "\
	file://0001-jpegparse-new-element.patch;patch=1 \
	file://0002-jpegparse-This-is-a-combination-of-18-commits.patch;patch=1 \
	file://0003-jpegparse-Thumbnail-parsing-support.patch;patch=1 \
	"
SRC_URI += "\
	file://0001-aacparse-New-implementation.patch;patch=1 \
	file://0002-aacparse-Add-framed-caps-increse-rank.patch;patch=1 \
	"

EXTRA_OECONF += "--disable-examples --disable-experimental --disable-sdl --disable-cdaudio \
		--with-plugins=flv,musicbrainz,wavpack,ivorbis,amrwbenc,libmms,freeze,rtpmanager,aacparse,qtmux,jpegparse"

ARM_INSTRUCTION_SET = "arm"

SRCREV = "b4d9b81b8a5d588be6ae634499a24a9be39a8aff"

# override the SRC_URI from gst-plugins-git.inc to pull from our
# fork (can be removed when jpegparser is integrated upstream):
#SRC_URI = "git://github.com/JJCG/gst-plugins-bad.git;protocol=git;branch=jpegparser3 \
#	   file://common-20091119.tar.gz \
#	   "
