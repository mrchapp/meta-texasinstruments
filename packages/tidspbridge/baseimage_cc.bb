DESCRIPTION = "Texas Instruments Baseimage."
LICENSE = "LGPL"
PR = "r1"

DEPENDS = " \
   baseimage-masterconfig \
   tidspbridge-samples \
   "

inherit xdc ccasefetch

PV = "4.0+cc+${SRCREV}"

CCASE_SPEC = "%\
   element /vobs/wtbu/OMAPSW_DSP/system/baseimage/...              ${SRCREV}%\
   element /vobs/wtbu/OMAPSW_DSP/system/avsync/syncclock/...       ${SRCREV}%\
   element /vobs/wtbu/OMAPSW_DSP/system/dasf/...                   ${SRCREV}%\
   element /vobs/wtbu/OMAPSW_DSP/system/hal/...                    ${SRCREV}%\
   element /vobs/wtbu/OMAPSW_DSP/system/inst2/...                  ${SRCREV}%\
   element /vobs/wtbu/OMAPSW_DSP/make/...                          ${SRCREV}%\
   element /vobs/wtbu/OMAPSW_DSP/audio/alg/SampleRateConverter/... ${SRCREV}%\
   element /vobs/wtbu/OMAPSW_DSP/system/tmon/...                   ${SRCREV}%\
   element /vobs/wtbu/OMAPSW_DSP/system/utils/...                  ${SRCREV}%\
   element * /main/LATEST%"


CCASE_PATHFETCH = " \
   /vobs/wtbu/OMAPSW_DSP/system/baseimage \
   /vobs/wtbu/OMAPSW_DSP/system/avsync/syncclock \
   /vobs/wtbu/OMAPSW_DSP/system/dasf \
   /vobs/wtbu/OMAPSW_DSP/system/hal \
   /vobs/wtbu/OMAPSW_DSP/system/inst2 \
   /vobs/wtbu/OMAPSW_DSP/make \
   /vobs/wtbu/OMAPSW_DSP/audio/alg/SampleRateConverter \
   /vobs/wtbu/OMAPSW_DSP/system/tmon \
   /vobs/wtbu/OMAPSW_DSP/system/utils \
   "

CCASE_PATHCOMPONENT = "wtbu"
CCASE_PATHCOMPONENTS = "1"

XDCPATH += "\
${STAGING_BINDIR}/dspbridge/dsp;\
${STAGING_BINDIR}/dspbridge/dsp/bdsptools/packages;\
${S}/OMAPSW_DSP;\
"        

XDCBUILDCFG="${S}/OMAPSW_DSP/make/config.bld"

XDCBUILDROOT="${S}/OMAPSW_DSP/system/baseimage"

do_compile_prepend() {
    # import the stupid masterconfig files
    mkdir -p ${S}/OMAPSW_DSP/include
    cp -fa ${STAGING_INCDIR}/dspbridge/include/MasterConfig.* ${S}/OMAPSW_DSP/include
}


# we need to stage baseimage so that we can later build socket nodes
do_stage() {
    install -d ${STAGING_BINDIR}/dspbridge
    cp -a ${S}/* ${STAGING_BINDIR}/dspbridge
}

do_install() {
    install -d ${D}${base_libdir}/dsp
    install -m 0644 ${S}/OMAPSW_DSP/system/baseimage/baseimage_tiomap4430.dof64T ${D}${base_libdir}/dsp
}

FILES_${PN}="${base_libdir}/dsp"
