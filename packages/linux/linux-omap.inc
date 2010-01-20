SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI OMAP devices"
LICENSE = "GPL"

KERNEL_OUTPUT = "arch/${ARCH}/boot/compressed/${KERNEL_IMAGETYPE}"

inherit kernel

COMPATIBLE_MACHINE = "omap-3430ldp|omap-3430sdp|omap-3630sdp|zoom2|zoom3"

do_configure_prepend() {
		case ${MACHINE} in
			omap-3430ldp)
				yes '' | oe_runmake omap_3430ldp_defconfig
				sed -i "s/# CONFIG_FB_OMAP_LCD_VGA is not set/CONFIG_FB_OMAP_LCD_VGA=y/" \
					${S}/.config
				;;
			omap-3430sdp)
				yes '' | oe_runmake omap_3430sdp_defconfig
#				sed -i "s/# CONFIG_FB_OMAP_LCD_VGA is not set/CONFIG_FB_OMAP_LCD_VGA=y/" \
#					${S}/.config
				;;
			omap-3630sdp)
				yes '' | oe_runmake omap_3630sdp_defconfig
				;;
			zoom2)
				yes '' | oe_runmake omap_zoom2_defconfig
				;;				
			zoom3)
			yes '' | oe_runmake omap_zoom3_defconfig
				;;				
			*)
				yes '' | oe_runmake ${MACHINE}_defconfig
				;;
		esac
	echo "${ADD_DISTRO_FEATURES}"
	${ADD_DISTRO_FEATURES}
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
              arch/arm/plat-omap/include/mach \
              arch/arm/plat-omap/include/plat \
              arch/arm/include/asm \
              arch/arm/include/asm/mach \
              arch/arm/include/asm/hardware \
              drivers/media/video/isp \
	      drivers/video/omap2/omapfb \
              include/trace \
              include/trace/events 
    do
        install -d ${STAGING_KERNEL_DIR}/$ii
        install -m 0644 ${S}/$ii/*.h ${STAGING_KERNEL_DIR}/$ii
    done
    install -m 0644 ${STAGING_KERNEL_DIR}/arch/arm/plat-omap/include/plat/*.h ${STAGING_KERNEL_DIR}/arch/arm/plat-omap/include/mach
    install -m 0644 ${S}/arch/arm/mach-omap1/include/mach/vmalloc.h ${STAGING_KERNEL_DIR}/arch/arm/plat-omap/include/mach

}

