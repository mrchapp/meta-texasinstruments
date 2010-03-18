PRIORITY = "optional"
DESCRIPTION = "Ittiam H264 Decoder Socket Node."
LICENSE = "LGPL"
PR = "r0"
DEPENDS += "tisocketnode-h264decitt-codec"

CCASE_SPEC = "%\
	      element /vobs/wtbu/CSSD_Linux_Releases/... ITTIAM-720P_REL_${PV}%\
	      element * /main/0%"

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_Linux_Releases/3430/Linux_23.I3.x/ittiam-sw/tisocketnode-h264decitt-20100311.tar.gz"
CCASE_PATHCOMPONENT = "ittiam-sw"
CCASE_PATHCOMPONENTS = "5"

SN_DIR=${S}/video/node/h264_ittiam/dec

inherit ccasefetch tisocketnode

do_unpack_ccase_append() {
	cd ${S}
	tar zxf tisocketnode-h264decitt-20100311.tar.gz
	mv vobs/wtbu/OMAPSW_DSP/video .
	rm -fr vobs
	rm -fr tisocketnode-h264decitt-20100311.tar.gz
	
	#FIX for 20100311 SN integration
	cd ${S}/video/node/h264_ittiam/dec
	rm -fr 720p_h264vdec_sn.pjt bin buildlog_omap3430release.txt \
	iv_h264vdec_sncfg_c.c make.mak makefile.linux out
}
