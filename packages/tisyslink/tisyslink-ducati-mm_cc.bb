PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Ducati Multimedia S/w"
LICENSE = "LGPL"
PR = "r2"

DEPENDS = " \
   tisyslink-ducati \
   titools-bios \
   titools-fc \
   titools-xdc \
   titools-ce \
   titools-xdais \
   titools-osal \  
   titools-cgtarm \
   "

inherit xdc ccasefetch

PV = "0.0+cc+${SRCREV}"

CCASE_SPEC = "%\
   element * ACT_DUCATI_OCT-PRERLS-20%\
   element /vobs/WTSD_DucatiMMSW/...   TI-MM-DUCATI_RLS.01.08.00.00%\
   element * /main/LATEST%"

# Note: WTSD_DucatiMMSW is used in the XDC package name, so it must be put
# in the ${S} folder
CCASE_PATHFETCH = "/vobs/WTSD_DucatiMMSW"
CCASE_PATHCOMPONENT = "vobs"
CCASE_PATHCOMPONENTS = "0"


SRC_URI += "file://padding.patch;patch=1"
SRC_URI += "file://jpegdec-fixes.patch;patch=1"
SRC_URI += "file://h264dec-needs-to-copy-width-height-from-input-port-t.patch;patch=1"
SRC_URI += "file://h264enc-needs-to-copy-width-height-from-input-port-t.patch;patch=1"

XDCPATH += "\
${STAGING_BINDIR}/syslink/ducati/ipc;\
${S}/WTSD_DucatiMMSW/framework;\
${S}/WTSD_DucatiMMSW/ext_rel/ivahd_codecs/packages;\
"

XDCBUILDROOT="${S}/WTSD_DucatiMMSW/platform/base_image"

XDCARGS="profile=debug core=app_m3"

XDCBUILDCFG="${S}/WTSD_DucatiMMSW/build/config.bld"


do_install() {
    install -d ${D}/syslink/ducati
    cd ${S}/WTSD_DucatiMMSW/platform/base_image
#    install -m 0644 out/app_m3/debug/base_image_app_m3.xem3 ${D}/syslink/ducati
#    install -m 0644 out/sys_m3/debug/base_image_sys_m3.xem3 ${D}/syslink/ducati
}

FILES_${PN}="/syslink/ducati"

