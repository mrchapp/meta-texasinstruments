PRIORITY = "optional"
DESCRIPTION = "Ittiam MPEG4 Decoder Socket Node."
LICENSE = "LGPL"
PR = "r0"
DEPENDS += "tisocketnode-mpeg4decitt-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/CSSD_Linux_Releases/... ITTIAM-720P_REL_${PV}%\
	      element * /main/0%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_Linux_Releases/3430/Linux_23.I3.x/ittiam-sw/tisocketnode-mpeg4decitt-3.7.tar.gz"
CCASE_PATHCOMPONENT = "ittiam-sw"
CCASE_PATHCOMPONENTS = "5"

SN_DIR=${S}/video/node/mpeg4_ittiam/dec

inherit ccasefetch tisocketnode

do_unpack_ccase_append() {
	cd ${S}
	tar zxf tisocketnode-mpeg4decitt-3.7.tar.gz
	mv vobs/wtbu/OMAPSW_DSP/video .
	rm -fr vobs
	rm -fr tisocketnode-mpeg4decitt-3.7.tar.gz
}
