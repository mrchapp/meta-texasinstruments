PRIORITY = "optional"
DESCRIPTION = "Ittiam MPEG4 Encoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	      element /vobs/wtbu/CSSD_Linux_Releases/... ITTIAM-720P_REL_${PV}%\
	      element * /main/0%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_Linux_Releases/3430/Linux_23.I3.x/ittiam-sw/tisocketnode-mpeg4encitt-codec-20100223.tar.gz"
CCASE_PATHCOMPONENT = "ittiam-sw"
CCASE_PATHCOMPONENTS = "5"

inherit ccasefetch

do_unpack_ccase_append() {
	cd ${S}
	tar zxf tisocketnode-mpeg4encitt-codec-20100223.tar.gz
	mv vobs/wtbu/CSSD_MM_Releases/Codecs/video/mpeg4_enc_ittiam/c64x/mm_tiicodecs/100_V_MPEG4_SP_720P_c64x+_1_05 .
	rm -fr vobs
	rm -fr tisocketnode-mpeg4encitt-codec-20100223.tar.gz
}

do_stage() {
        chmod -R +w ${S}/*
	rm -rf ${STAGING_BINDIR}/dspbridge/Codecs/video/mpeg4_enc_ittiam/   
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/video/mpeg4_enc_ittiam/c64x/mm_tiicodecs/100_V_MPEG4_SP_720P_c64x+_1_05
	cp -a ${S}/100_V_MPEG4_SP_720P_c64x+_1_05/* ${STAGING_BINDIR}/dspbridge/Codecs/video/mpeg4_enc_ittiam/c64x/mm_tiicodecs/100_V_MPEG4_SP_720P_c64x+_1_05
}
