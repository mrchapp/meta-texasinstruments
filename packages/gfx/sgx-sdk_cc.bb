SECTION = "libs"
PRIORITY = "optional"
DESCRIPTION = "Imagination Technologies SGX PowerVR SDK"
LICENSE = "GPL"
PR = "r0"
COMPATIBLE_MACHINE = "omap-4430sdp"
RDEPENDS = "sgx-lib-noxws"

inherit ccasefetch

PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN} ${PN}-dbg"
FILES_${PN} = "${bindir}/* ${libdir}/* ${sysconfdir}/* "
FILES_${PN}-dbg = "${bindir}/.debug/* ${libdir}/.debug/*"

PV = "0.0+cc+${SRCREV}"

CCASE_SPEC = "%\
	element * COMPONENT_ROOT%\
	element /vobs/wtbu/OMAPSW_GFX/... ${SRCREV}%\
"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_GFX/IMAGINATION/GFX/GFX_Linux_SDK"
CCASE_PATHCOMPONENT = "GFX_Linux_SDK"
CCASE_PATHCOMPONENTS = "5"

S = ${WORKDIR}/sgx-sdk-*

do_compile() {
    export PLATFORM=LinuxOMAP4
    export LIBDIR=${STAGING_LIBDIR}
    for co in OGLES OGLES2 OVG; do
        export SDKDIR=${S}/$co/SDKPackage/
        for de in Demos ; do
            if [ -d ${SDKDIR}/$de ]; then
                for di in ${SDKDIR}/$de/*; do
                    if [ -d $di/$co/Build/LinuxGeneric ]; then
                        cd $di/$co/Build/LinuxGeneric
                        make Common=1
                    fi
                done
            fi
        done
    done
}



