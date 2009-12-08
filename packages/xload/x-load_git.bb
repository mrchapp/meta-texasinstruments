SECTION = "bootloaders"
PRIORITY = "optional"
DESCRIPTION = "Texas Instruments X-Loader boot utility"
LICENSE = "GPL"
PR="r2"
DEPENDS="u-boot"

DEFAULT_PREFERENCE = "1"

XLOAD_MACHINE_omap-4430sdp = "omap4430sdp_config"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "omap-4430sdp"

PV = "0.0+git+${SRCREV}"

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"
PARALLEL_MAKE = ""

XLOAD_IMAGE_ELF ?= "${PN}-${MACHINE}-${PV}-${PR}-${DATETIME}"
XLOAD_IMAGE ?= "${XLOAD_IMAGE_ELF}.bin"
XLOAD_IMAGE_CH ?= "${XLOAD_IMAGE_ELF}.ch.bin"
XLOAD_SYMLINK_ELF ?= "${PN}-${MACHINE}"
XLOAD_SYMLINK ?= "${XLOAD_SYMLINK_ELF}.bin"
XLOAD_SYMLINK_CH ?= "${XLOAD_SYMLINK_ELF}.ch.bin"

XLOAD_MLO_IMAGE ?= "MLO-${MACHINE}-${PV}-${PR}-${DATETIME}"
XLOAD_MLO_SYMLINK ?= "MLO"

S = "${WORKDIR}/git"

SRC_URI = "git://dev.omapzoom.org/pub/scm/bootloader/x-loader.git;branch=omap4_dev;protocol=git \
           file://configuration-header.bin \
	       file://mlo_sdmmc_or_nfs.patch;patch=1 \
"


do_configure() {
	cd ${S}/include
	ln -sf ${STAGING_INCDIR}/u-boot/command.h
	ln -sf ${STAGING_INCDIR}/u-boot/fat.h
	ln -sf ${STAGING_INCDIR}/u-boot/ide.h
	ln -sf ${STAGING_INCDIR}/u-boot/malloc.h
	ln -sf ${STAGING_INCDIR}/u-boot/mmc.h
	ln -sf ${STAGING_INCDIR}/u-boot/part.h
	ln -sf ${STAGING_INCDIR}/u-boot/environment.h
	ln -sf ${STAGING_INCDIR}/u-boot/i2c.h

	cd ${S}/include/asm
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/byteorder.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/types.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/mach-types.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/io.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/memory.h

	cd ${S}/include/asm/arch-omap4
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap4/bits.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap4/clocks.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap4/clocks343x.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap4/cpu.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap4/mem.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap4/mux.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap4/omap4430.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap4/sizes.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap4/sys_info.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap4/sys_proto.h
	ln -sf ${STAGING_INCDIR}/u-boot/asm-arm/arch-omap4/rev.h

	cd ${S}/include/linux
	ln -sf ${STAGING_INCDIR}/u-boot/linux/stat.h
	ln -sf ${STAGING_INCDIR}/u-boot/linux/time.h
	ln -sf ${STAGING_INCDIR}/u-boot/linux/byteorder
}

do_compile () {
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS
	oe_runmake ${XLOAD_MACHINE}
	oe_runmake all
	oe_runmake ift
}

do_deploy () {
	install -d ${DEPLOY_DIR_IMAGE}
	install ${S}/x-load     ${DEPLOY_DIR_IMAGE}/${XLOAD_IMAGE_ELF}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_IMAGE_ELF}
	install ${S}/x-load.bin ${DEPLOY_DIR_IMAGE}/${XLOAD_IMAGE}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_IMAGE}
	install ${S}/MLO ${DEPLOY_DIR_IMAGE}/${XLOAD_MLO_IMAGE}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_IMAGE}
	cp ${WORKDIR}/configuration-header.bin ${S}/x-load.ch.bin
	cat ${S}/MLO >> ${S}/x-load.ch.bin
	install ${S}/x-load.ch.bin ${DEPLOY_DIR_IMAGE}/${XLOAD_IMAGE_CH}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_IMAGE_CH}

	cd ${DEPLOY_DIR_IMAGE}
	rm -f ${XLOAD_SYMLINK_ELF}
	ln -sf ${XLOAD_IMAGE_ELF} ${XLOAD_SYMLINK_ELF}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_SYMLINK_ELF}
	rm -f ${XLOAD_SYMLINK}
	ln -sf ${XLOAD_IMAGE} ${XLOAD_SYMLINK}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_SYMLINK}
	rm -f ${XLOAD_MLO_SYMLINK}
	ln -sf ${XLOAD_MLO_IMAGE} ${XLOAD_MLO_SYMLINK}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_SYMLINK}
	rm -f ${XLOAD_SYMLINK_CH}
	ln -sf ${XLOAD_IMAGE_CH} ${XLOAD_SYMLINK_CH}
	package_stagefile_shell ${DEPLOY_DIR_IMAGE}/${XLOAD_SYMLINK_CH}
}

do_deploy[dirs] = "${S}"
addtask deploy before do_build after do_compile

