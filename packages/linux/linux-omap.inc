SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI OMAP devices"
LICENSE = "GPL"

inherit kernel

COMPATIBLE_MACHINE = "omap-4430sdp"
KERNEL_IMAGETYPE = "uImage"
KERNEL_OUTPUT = "arch/${ARCH}/boot/${KERNEL_IMAGETYPE}"

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
        sed -e '/CONFIG_AEABI/d' \
            -e '/CONFIG_OABI_COMPAT=/d' \
            '${WORKDIR}/defconfig-${MACHINE}' >>'${S}/.config'

        yes '' | oe_runmake oldconfig

    else
        case ${MACHINE} in
            omap-4430sdp)
                yes '' | oe_runmake omap_4430sdp_defconfig
                ;;
            *)
                yes '' | oe_runmake ${MACHINE}_defconfig
                ;;
        esac
    fi
}


do_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}
    install -m 0644 arch/${ARCH}/boot/${KERNEL_IMAGETYPE} ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}-${PV}-${MACHINE}-${DATETIME}.bin
    install -m 0644 vmlinux ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}-${DATETIME}
    cd ${DEPLOY_DIR_IMAGE}
    ln -sf ${KERNEL_IMAGETYPE}-${PV}-${MACHINE}-${DATETIME}.bin ${KERNEL_IMAGETYPE}-${MACHINE}.bin
    tar -cvzf ${DEPLOY_DIR_IMAGE}/modules-${KERNEL_RELEASE}-${MACHINE}.tgz -C ${D} lib
}

do_deploy[dirs] = "${S}"

addtask deploy before do_populate_staging after do_install

do_stage_append() {

    for ii in arch/arm/mach-omap2 \
              arch/arm/plat-omap/include/dspbridge \
              arch/arm/plat-omap/include/syslink \
              arch/arm/plat-omap/include/plat \
              arch/arm/include/asm \
              arch/arm/include/asm/mach \
              arch/arm/include/asm/hardware \
              drivers/media/video/tiler \
              drivers/video/omap2/omapfb \
              include/generated \
              include/trace \
              include/trace/events
    do
        install -d ${STAGING_KERNEL_DIR}/$ii
        install -m 0644 ${S}/$ii/*.h ${STAGING_KERNEL_DIR}/$ii
    done

}

