PRIORITY = "optional"
DESCRIPTION = "Ittiam MPEG4 Decoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	      element /vobs/wtbu/CSSD_Linux_Releases/... ITTIAM-720P_REL_${PV}%\
	      element * /main/0%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_Linux_Releases/3430/Linux_23.I3.x/ittiam-sw/tisocketnode-mpeg4decitt-codec-20100308.tar.gz"
CCASE_PATHCOMPONENT = "ittiam-sw"
CCASE_PATHCOMPONENTS = "5"

inherit ccasefetch

do_unpack_ccase_append() {
	cd ${S}
	tar zxf tisocketnode-mpeg4decitt-codec-20100308.tar.gz
	mv vobs/wtbu/CSSD_MM_Releases/Codecs/video/mpeg4_dec_ittiam/c64x/mm_tiicodecs/ITT_MPEG4_DEC.zip .
	rm -fr vobs
	rm -fr tisocketnode-mpeg4decitt-codec-20100308.tar.gz
}

do_compile() {
	unzip ITT_MPEG4_DEC.zip
}

do_stage() {
        chmod -R +w ${S}/*
	rm -rf ${STAGING_BINDIR}/dspbridge/Codecs/video/mpeg4_dec_ittiam/   
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/video/mpeg4_dec_ittiam/c64x/mm_tiicodecs/ITT_MPEG4_DEC
	cp -a ${S}/ITT_MPEG4_DEC/* ${STAGING_BINDIR}/dspbridge/Codecs/video/mpeg4_dec_ittiam/c64x/mm_tiicodecs/ITT_MPEG4_DEC
}
