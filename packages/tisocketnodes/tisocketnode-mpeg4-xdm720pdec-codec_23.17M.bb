PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPEG4 w/720p Decoder Socket Node Codec."
LICENSE = "LGPL"
PR = "r0"
DEPENDS = "baseimage"

require tisocketnode-cs_${PV}.inc

CCASE_PATHFETCH = "/vobs/wtbu/CSSD_MM_Releases/Codecs/video/mpeg4_ari_X_dec"
CCASE_PATHCOMPONENT = "mpeg4_ari_X_dec"
CCASE_PATHCOMPONENTS = "5"

inherit ccasefetch

do_compile() {
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_BINDIR}/dspbridge/Codecs/video/mpeg4_ari_X_dec
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge/Codecs/video/mpeg4_ari_X_dec
}
