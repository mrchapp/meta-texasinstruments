PRIORITY = "optional"
DESCRIPTION = "Texas Instruments Ducati Multimedia S/w"
LICENSE = "LGPL"
PR = "r0"

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
   element * ACT_DUCATI_OCT-PRERLS-19%\
   element /vobs/WTSD_DucatiMMSW/...   ${SRCREV}%\
   element * /main/LATEST%"

# Note: WTSD_DucatiMMSW is used in the XDC package name, so it must be put
# in the ${S} folder
CCASE_PATHFETCH = "/vobs/WTSD_DucatiMMSW"
CCASE_PATHCOMPONENT = "vobs"
CCASE_PATHCOMPONENTS = "0"

XDCPATH += "\
${STAGING_BINDIR}/syslink/ducati/ipc;\
${S}/WTSD_DucatiMMSW/framework;\
${S}/WTSD_DucatiMMSW/ext_rel/ivahd_codecs/packages;\
"

XDCBUILDROOT="${S}/WTSD_DucatiMMSW/platform/base_image"

XDCARGS="profile=debug core=sys_m3 core=app_m3"

XDCBUILDCFG="${S}/WTSD_DucatiMMSW/build/config.bld"

FILES_${PN}="/syslink/"

do_install() {
    install -d ${D}/syslink
    cd ${S}/WTSD_DucatiMMSW/platform/base_image
	for xem3 in ./out/app_m3/debug/base_image_app_m3.xem3 ./out/sys_m3/debug/base_image_sys_m3.xem3; do
		cp ${xem3} ${xem3}.old
        ${STAGING_BINDIR_NATIVE}/titools/cgtarm-*/bin/strip470 ${xem3}
        install -m 0644 ${xem3}      ${D}/syslink/
		mv ${xem3}.old ${xem3}
	done
}

