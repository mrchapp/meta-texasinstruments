SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI OMAP devices"
LICENSE = "GPL"

KERNEL_OUTPUT = "arch/${ARCH}/boot/compressed/${KERNEL_IMAGETYPE}"

inherit kernel

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp|omap-3630sdp|zoom2|zoom3|omap-4430sdp"

ADD_DISTRO_FEATURES = " ${@base_contains("DISTRO_FEATURES", "720p", "sed -i 's/# CONFIG_FB_OMAP_720P_STREAMING is not set/CONFIG_FB_OMAP_720P_STREAMING=y/' ${S}/.config;", "", d)}"

do_configure_prepend() {

    if [ -f '${WORKDIR}/defconfig-${MACHINE}' ]; then
        rm -f ${S}/.config || true

        if [ "${TARGET_OS}" = "linux-gnueabi" -o  "${TARGET_OS}" = "linux-uclibcgnueabi" ]; then
            echo "CONFIG_AEABI=y"                   >> ${S}/.config
            echo "CONFIG_OABI_COMPAT=y"             >> ${S}/.config
        else
            echo "# CONFIG_AEABI is not set"        >> ${S}/.config
            echo "# CONFIG_OABI_COMPAT is not set"  >> ${S}/.config
        fi

        sed     -e '/CONFIG_AEABI/d' \
            -e '/CONFIG_OABI_COMPAT=/d' \
            '${WORKDIR}/defconfig-${MACHINE}' >>'${S}/.config'

        yes '' | oe_runmake oldconfig
    else
        case ${MACHINE} in
            omap-3430ldp)
                yes '' | oe_runmake omap_3430ldp_defconfig
                sed -i "s/# CONFIG_FB_OMAP_LCD_VGA is not set/CONFIG_FB_OMAP_LCD_VGA=y/" \
                    ${S}/.config
                ;;
            omap-3430sdp)
                yes '' | oe_runmake omap_3430sdp_defconfig
#                sed -i "s/# CONFIG_FB_OMAP_LCD_VGA is not set/CONFIG_FB_OMAP_LCD_VGA=y/" \
#                    ${S}/.config
                ;;
            omap-3630sdp)
                yes '' | oe_runmake omap_3630sdp_defconfig
                ;;
            zoom2)
                yes '' | oe_runmake omap_zoom2_defconfig
                ;;
            zoom3)
                echo "CONFIG_MPU_BRIDGE=m" >> ${S}/arch/arm/configs/omap_zoom3_defconfig
                echo "CONFIG_BRIDGE_MEMPOOL_SIZE=0x600000" >> ${S}/arch/arm/configs/omap_zoom3_defconfig                
                yes '' | oe_runmake omap_zoom3_defconfig
                ;;
            omap-4430sdp)
                yes '' | oe_runmake omap_4430sdp_defconfig
                ;;
            *)
                yes '' | oe_runmake ${MACHINE}_defconfig
                ;;
        esac
    fi
    ${ADD_DISTRO_FEATURES}
}


do_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}
    install -m 0644 arch/${ARCH}/boot/${KERNEL_IMAGETYPE} ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}-${PV}-${MACHINE}-${DATETIME}.bin
#    install -m 0644 vmlinux ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}-${DATETIME}
    cd ${DEPLOY_DIR_IMAGE}
    ln -sf ${KERNEL_IMAGETYPE}-${PV}-${MACHINE}-${DATETIME}.bin ${KERNEL_IMAGETYPE}-${MACHINE}.bin
    tar -cvzf ${DEPLOY_DIR_IMAGE}/modules-${KERNEL_RELEASE}-${MACHINE}.tgz -C ${D} lib
#    case ${MACHINE} in
#                omap-4430sdp)
#    #        ln -sf kernel-${PV}-${MACHINE}-${DATETIME}.bin kernel-${MACHINE}.bin
#            install -d ${STAGING_LIBDIR}/modules ${D}/lib/modules
#            tar -C ${D}/lib -cf - modules | tar -C ${STAGING_LIBDIR} -xf -
#            oenote "Modules in ${D}/lib ..."
#            oenote "Modules copied to ${STAGING_LIBDIR} "
#            ;;
#    esac
}

do_deploy[dirs] = "${S}"

addtask deploy before do_populate_staging after do_install

do_stage_append() {

    for ii in arch/arm/mach-omap2 \
              arch/arm/plat-omap/include/dspbridge \
              arch/arm/plat-omap/include/mach \
              arch/arm/plat-omap/include/syslink \
              arch/arm/include/asm \
              arch/arm/include/asm/mach \
              arch/arm/include/asm/hardware \
              drivers/media/video/tiler \
              include/trace \
              include/trace/events
    do
        install -d ${STAGING_KERNEL_DIR}/$ii
        install -m 0644 ${S}/$ii/*.h ${STAGING_KERNEL_DIR}/$ii
    done

}


