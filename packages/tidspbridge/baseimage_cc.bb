PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Baseimage."
LICENSE = "LGPL"
PR = "r0"

DEPENDS = " \
   baseimage-masterconfig \
   tidspbridge-samples \
"


inherit ccasefetch

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

CCASE_PATHCOMPONENT = "OMAPSW_DSP"
CCASE_PATHCOMPONENTS = "2"

ENV_VAR = " \
   DEPOT=${STAGING_BINDIR}/dspbridge/tools \
   DSPMAKEROOT=${S}/make \
   DBS_BRIDGE_DIR_C64=${STAGING_BINDIR}/dspbridge/dsp \
   "

SRC_URI = "file://baseimage-linux-build.patch;patch=1"
        

do_compile() {

   cd ${S}/system/baseimage

## Getting system files
#  cp -fa ${STAGING_BINDIR}/dspbridge/system/utils/* ${S}/system/utils
#  cp -fa ${STAGING_BINDIR}/dspbridge/system/inst2/* ${S}/system/inst2
#  cp -fa ${STAGING_BINDIR}/dspbridge/system/avsync/* ${S}/system/avsync
#  cp -fa ${STAGING_BINDIR}/dspbridge/system/dasf/* ${S}/system/dasf
#  cp -fa ${STAGING_BINDIR}/dspbridge/system/hal/* ${S}/system/hal
#  cp -fa ${STAGING_BINDIR}/dspbridge/system/tmon/* ${S}/system/tmon
#
#  mkdir -p ${S}/make 
#  cp -fa ${STAGING_BINDIR}/dspbridge/make/* ${S}/make
#
#  mkdir -p ${S}/audio/alg/SampleRateConverter
#  cp -fa ${STAGING_BINDIR}/dspbridge/audio/alg/SampleRateConverter/* ${S}/audio/alg/SampleRateConverter    
#

  # import the stupid masterconfig files
  mkdir -p ${S}/include
  cp -fa ${STAGING_INCDIR}/dspbridge/include/MasterConfig.* ${S}/include

  ${ENV_VAR} oe_runmake -f gmakefile omap4430
}


# we need to stage baseimage so that we can later build socket nodes
do_stage() {
	install -d ${STAGING_BINDIR}/dspbridge
	cp -a ${S}/* ${STAGING_BINDIR}/dspbridge
}

do_install() {
  install -d ${D}${base_libdir}/dsp
  install -m 0644 ${S}/system/baseimage/src/baseimage_tiomap4430.dof64T ${D}${base_libdir}/dsp
}

FILES_${PN}="${base_libdir}/dsp"
