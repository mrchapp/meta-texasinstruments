
PRIORITY = "optional"
DESCRIPTION = "Ittiam H264 Decoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

CCASE_SPEC = "%\
	      element /vobs/wtbu/CSSD_Linux_Releases/... ITTIAM-720P_REL_${PV}%\
	      element * /main/0%\
	     "

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_Linux_Releases/3430/Linux_23.I3.x/ittiam-sw/tisocketnode-h264decitt-codec-3.7.tar.gz"
CCASE_PATHCOMPONENT = "ittiam-sw"
CCASE_PATHCOMPONENTS = "5"

inherit ccasefetch

do_unpack_ccase_append() {
	cd ${S}
	tar zxf tisocketnode-h264decitt-codec-3.7.tar.gz
	mv vobs/wtbu/CSSD_MM_Releases/Codecs/video/h264_dec_ittiam/c64x/mm_tiicodecs/100_V_H264AVC_D_BP_c64x+_1_05.zip .
	rm -fr vobs
	rm -fr tisocketnode-h264decitt-codec-3.7.tar.gz
}

do_compile() {
	unzip 100_V_H264AVC_D_BP_c64x+_1_05.zip
}

do_stage() {
        chmod -R +w ${S}/*
	rm -rf ${STAGING_BINDIR}/dspbridge/Codecs/video/h264_dec_ittiam/   
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/video/h264_dec_ittiam/c64x/mm_tiicodecs/100_V_H264AVC_D_BP_c64x+_1_05
	cp -a ${S}/100_V_H264AVC_D_BP_c64x+_1_05/* ${STAGING_BINDIR}/dspbridge/Codecs/video/h264_dec_ittiam/c64x/mm_tiicodecs/100_V_H264AVC_D_BP_c64x+_1_05
}
