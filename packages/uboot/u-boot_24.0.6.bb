require u-boot.inc
inherit ccasefetch

PR="r0"
#S=${WORKDIR}/${PN}

CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_L/u-boot/...  LINUX-U-BOOT_RLS_${PV} %\
	element * /main/LATEST %\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/u-boot"
CCASE_PATHCOMPONENT = "u-boot"
CCASE_PATHCOMPONENTS = "3"

UBOOT_MACHINE_omap-3430ldp = "omap3430labrador_config"
UBOOT_MACHINE_omap-3430sdp = "omap3430sdp_config"
UBOOT_MACHINE_omap-4430sdp = "omap4430sdp_config"
UBOOT_MACHINE_omap-4430sdphigh = "omap4430sdphigh_config"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "omap-4430sdp|omap-4430sdphigh"

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

	install -m 644 ${S}/include/linux/byteorder/* \
		${STAGING_INCDIR}/${PN}/linux/byteorder/
	install -m 644 ${S}/include/linux/stat.h ${STAGING_INCDIR}/${PN}/linux/
	install -m 644 ${S}/include/linux/time.h ${STAGING_INCDIR}/${PN}/linux/

#	[ -s ${STAGING_INCDIR}/${PN}/asm ] ||
#		ln -sf asm-arm ${STAGING_INCDIR}/${PN}/asm

#	[ -s ${STAGING_INCDIR}/${PN}/asm-arm/arch ] ||
#		ln -sf arch-omap3 ${STAGING_INCDIR}/${PN}/asm-arm/arch
}
