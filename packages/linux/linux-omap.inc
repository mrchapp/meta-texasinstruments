SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI OMAP devices"
LICENSE = "GPL"

KERNEL_OUTPUT = "arch/${ARCH}/boot/compressed/${KERNEL_IMAGETYPE}"

inherit kernel

COMPATIBLE_MACHINE = "omap-3430(l|s)dp|omap-4430sdp"

ADD_DISTRO_FEATURES_omap-3430ldp ?= "	${@base_contains("DISTRO_FEATURES", "720p", "sed -i 's/# CONFIG_FB_OMAP_720P_STREAMING is not set/CONFIG_FB_OMAP_720P_STREAMING=y/' ${S}/.config;", "", d)}"

ADD_DISTRO_FEATURES_omap-3430sdp ?= "   ${@base_contains("DISTRO_FEATURES", "720p", "sed -i 's/# CONFIG_FB_OMAP_720P_STREAMING is not set/CONFIG_FB_OMAP_720P_STREAMING=y/' ${S}/.config;", "", d)}"


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
                                sed -i "s/# CONFIG_FB_OMAP_LCD_VGA is not set/CONFIG_FB_OMAP_LCD_VGA=y/" \
                                        ${S}/.config
                                ;;
                        omap-4430sdp)
                                yes '' | oe_runmake omap_4430sdp_defconfig
				sed -i 's/# CONFIG_MPU_BRIDGE is not set/CONFIG_MPU_BRIDGE=m/' ${S}/.config
				echo "CONFIG_BRIDGE_MEMPOOL_SIZE=0x600000" >> ${S}/.config
				echo "CONFIG_BRIDGE_DEBUG=y"        >> ${S}/.config
				sed -i 's/CONFIG_CMDLINE="root=\/dev\/ram0 rw mem=128M console=ttyS0,115200n8 initrd=0x81600000,20M ramdisk_size=20480"/CONFIG_CMDLINE="root=\/dev\/ram0 rw mem=128M console=ttyS0,115200n8 initrd=0x81600000,54M ramdisk_size=55296"/' ${S}/.config

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
	#case ${MACHINE} in
	#	omap-4430sdp)
	#		${AR%-*}-objcopy -O binary -R .note -R .note.gnu.build-id -R .comment -S vmlinux ${DEPLOY_DIR_IMAGE}/kernel-${PV}-${MACHINE}-${DATETIME}.bin
	#		;;
	#esac
	cd ${DEPLOY_DIR_IMAGE}
	ln -sf ${KERNEL_IMAGETYPE}-${PV}-${MACHINE}-${DATETIME}.bin ${KERNEL_IMAGETYPE}-${MACHINE}.bin
	tar -cvzf ${DEPLOY_DIR_IMAGE}/modules-${KERNEL_RELEASE}-${MACHINE}.tgz -C ${D} lib
	case ${MACHINE} in
                omap-4430sdp)
	#		ln -sf kernel-${PV}-${MACHINE}-${DATETIME}.bin kernel-${MACHINE}.bin
			install -d ${STAGING_LIBDIR}/modules ${D}/lib/modules
			tar -C ${D}/lib -cf - modules | tar -C ${STAGING_LIBDIR} -xf -
			oenote "Modules in ${D}/lib ..."
			oenote "Modules copied to ${STAGING_LIBDIR} "
			;;
	esac
}

do_deploy[dirs] = "${S}"

addtask deploy before do_populate_staging after do_install
