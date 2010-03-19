require u-boot.inc

PR="r7"

SRC_URI = "git://git.omapzoom.org/repo/u-boot.git;branch=omap4_dev;protocol=git \
	   file://uboot_sdmmc_or_nfs.patch;patch=1 \
	   file://0001-Adding-Saveenv-feature-for-eMMC-on-OMAP4SDP.patch;patch=1 \
"

S = "${WORKDIR}/git"

UBOOT_MACHINE_omap-4430sdp = "omap4430sdp_config"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "omap-4430sdp"

do_stage() {
	install -d ${STAGING_BINDIR_NATIVE}
	install -m 755 tools/mkimage ${STAGING_BINDIR_NATIVE}/

	install -d ${STAGING_INCDIR}/${PN} \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap4 \
		${STAGING_INCDIR}/${PN}/linux \
		${STAGING_INCDIR}/${PN}/linux/byteorder

	install -m 644 ${S}/include/command.h ${STAGING_INCDIR}/${PN}/
	install -m 644 ${S}/include/fat.h ${STAGING_INCDIR}/${PN}/
	install -m 644 ${S}/include/ide.h ${STAGING_INCDIR}/${PN}/
	install -m 644 ${S}/include/malloc.h ${STAGING_INCDIR}/${PN}/
	install -m 644 ${S}/include/mmc.h ${STAGING_INCDIR}/${PN}/
	install -m 644 ${S}/include/part.h ${STAGING_INCDIR}/${PN}/
	install -m 644 ${S}/include/environment.h ${STAGING_INCDIR}/${PN}/

	install -m 644 ${S}/include/asm-arm/byteorder.h \
		${STAGING_INCDIR}/${PN}/asm-arm/

	install -m 644 ${S}/include/asm-arm/arch-omap4/bits.h  \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap4/
	install -m 644 ${S}/include/asm-arm/arch-omap4/clocks443x.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap4/
	install -m 644 ${S}/include/asm-arm/arch-omap4/clocks.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap4/
	install -m 644 ${S}/include/asm-arm/arch-omap4/cpu.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap4/
	install -m 644 ${S}/include/asm-arm/arch-omap4/mem.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap4/
	install -m 644 ${S}/include/asm-arm/arch-omap4/mux.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap4/
	install -m 644 ${S}/include/asm-arm/arch-omap4/omap4430.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap4/
	install -m 644 ${S}/include/asm-arm/arch-omap4/sizes.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap4/
	install -m 644 ${S}/include/asm-arm/arch-omap4/sys_info.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap4/
	install -m 644 ${S}/include/asm-arm/arch-omap4/sys_proto.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap4/
	install -m 644 ${S}/include/asm-arm/arch-omap4/rev.h \
		${STAGING_INCDIR}/${PN}/asm-arm/arch-omap4/
	install -m 644 ${S}/include/asm-arm/types.h \
		${STAGING_INCDIR}/${PN}/asm-arm/
	install -m 644 ${S}/include/asm-arm/mach-types.h \
		${STAGING_INCDIR}/${PN}/asm-arm/
	install -m 644 ${S}/include/asm-arm/io.h \
		${STAGING_INCDIR}/${PN}/asm-arm/
	install -m 644 ${S}/include/asm-arm/memory.h \
		${STAGING_INCDIR}/${PN}/asm-arm/
	install -m 644 ${S}/include/i2c.h \
		${STAGING_INCDIR}/${PN}/

	install -m 644 ${S}/include/linux/byteorder/* \
		${STAGING_INCDIR}/${PN}/linux/byteorder/
	install -m 644 ${S}/include/linux/stat.h ${STAGING_INCDIR}/${PN}/linux/
	install -m 644 ${S}/include/linux/time.h ${STAGING_INCDIR}/${PN}/linux/

#	[ -s ${STAGING_INCDIR}/${PN}/asm ] ||
#		ln -sf asm-arm ${STAGING_INCDIR}/${PN}/asm

#	[ -s ${STAGING_INCDIR}/${PN}/asm-arm/arch ] ||
#		ln -sf arch-omap3 ${STAGING_INCDIR}/${PN}/asm-arm/arch
}
