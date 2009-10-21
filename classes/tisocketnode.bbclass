
inherit xdc ccasefetch

DEPENDS += "baseimage"


XDCPATH += "\
${S};\
${S}/OMAPSW_DSP;\
${STAGING_BINDIR}/dspbridge/dsp;\
${STAGING_BINDIR}/dspbridge/dsp/bdsptools/packages;\
${STAGING_BINDIR}/dspbridge;\
${STAGING_BINDIR}/dspbridge/OMAPSW_DSP;\
"        

XDCBUILDCFG="${STAGING_BINDIR}/dspbridge/OMAPSW_DSP/make/config.bld"

XDCBUILDROOT="${SN_DIR}"

do_install() {
    install -d ${D}${base_libdir}/dsp
    install -m 0644 ${SN_DIR}/*.dll64T ${D}${base_libdir}/dsp
}

FILES_${PN}="${base_libdir}/dsp"


