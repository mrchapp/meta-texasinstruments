require gst-plugins.inc

# override the SRC_URI from gst-plugins.inc to pull from git:
SRC_URI = "git://anongit.freedesktop.org/gstreamer/${PN};protocol=git \
           file://common-20090628.tar.gz \
          "
S = "${WORKDIR}/git"

do_configure_prepend() {
        # This m4 file contains nastiness which conflicts with libtool 2.2.2
        rm -f ${S}/common/m4/lib-link.m4 || true
	# some special magic to work-around bitbake's lack of support for git-modules:
        mv ${WORKDIR}/common-20090628/* ${S}/common/
        (cd ${S}/; NOCONFIGURE=1 ./autogen.sh)
}

