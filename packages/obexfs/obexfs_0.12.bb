DESCRIPTION = "OBEXFS based on openobex."
SECTION = "libs"
LICENSE = "GPL"
PR = "r0"

DEPENDS = "openobex fuse obexftp"

SRC_URI = "http://downloads.sourceforge.net/project/openobex/obexfs/${PV}/obexfs-${PV}.tar.gz \
	  "

S = "${WORKDIR}/obexfs-0.12" 

inherit autotools pkgconfig gconf

EXTRA_OECONF += "--enable-bluetooth --disable-swig --disable-perl --disable-python --disable-tcl --disable-builddocs --disable-rpath"

PARALLEL_MAKE = ""

do_stage() {
        autotools_stage_all
} 

