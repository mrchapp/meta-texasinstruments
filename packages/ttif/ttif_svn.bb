SECTION = "libs"
DESCRIPTION = "Test and Trace InterFace"
PR = "r1"

SRC_URI = "svn://plato.googlecode.com/svn/trunk/nonjava/target;module=${PN};rev=14313;proto=http"
S = ${WORKDIR}/${PN}

inherit autotools pkgconfig

do_stage() {
	autotools_stage_all
}

