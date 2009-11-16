require u-boot.inc
 
PR = "r0"
 
#SRC_URI = "git://git.omapzoom.org/repo/u-boot.git;branch=zoom3;protocol=git \
#"

SRC_URI = " \
${@base_contains("MACHINE", "omap-3630sdp", "git://git.omapzoom.org/repo/u-boot.git;branch=3630v0.1;protocol=git", "", d)} \
${@base_contains("MACHINE", "zoom3", "git://git.omapzoom.org/repo/u-boot.git;branch=master;protocol=git", "", d)} \
"

PV = "git${SRCREV}"

COMPATIBLE_MACHINE = "omap-3430(l|s)dp|omap-3630sdp|zoom2|zoom3"
 
UBOOT_MACHINE_omap-3430ldp = "omap3430labrador_config"
UBOOT_MACHINE_omap-3430sdp = "omap3430sdp_config"
UBOOT_MACHINE_omap-3630sdp = "omap3630sdp_config"
UBOOT_MACHINE_zoom2 ="omap3430zoom2_config"
UBOOT_MACHINE_zoom3 ="omap3630zoom3_config"

 
S=${WORKDIR}/git
PACKAGE_ARCH = "${MACHINE_ARCH}"
 
do_stage() {
install -d ${STAGING_BINDIR_NATIVE}
install -m 755 tools/mkimage ${STAGING_BINDIR_NATIVE}/
 
install -d ${STAGING_INCDIR}/${PN} \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3 \
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
 
install -m 644 ${S}/include/asm-arm/arch-omap3/bits.h \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
install -m 644 ${S}/include/asm-arm/arch-omap3/clocks343x.h \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
install -m 644 ${S}/include/asm-arm/arch-omap3/clocks.h \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
install -m 644 ${S}/include/asm-arm/arch-omap3/cpu.h \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
install -m 644 ${S}/include/asm-arm/arch-omap3/mem.h \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
install -m 644 ${S}/include/asm-arm/arch-omap3/mux.h \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
install -m 644 ${S}/include/asm-arm/arch-omap3/omap3430.h \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
install -m 644 ${S}/include/asm-arm/arch-omap3/sizes.h \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
install -m 644 ${S}/include/asm-arm/arch-omap3/sys_info.h \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
install -m 644 ${S}/include/asm-arm/arch-omap3/sys_proto.h \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
install -m 644 ${S}/include/asm-arm/arch-omap3/rev.h \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
if [ "${MACHINE}" = "zoom3" ]; then
install -m 644 ${S}/include/asm-arm/arch-omap3/dpll_table_36xx.S \
${STAGING_INCDIR}/${PN}/asm-arm/arch-omap3/
fi

install -m 644 ${S}/include/linux/byteorder/* \
${STAGING_INCDIR}/${PN}/linux/byteorder/
install -m 644 ${S}/include/linux/stat.h ${STAGING_INCDIR}/${PN}/linux/
install -m 644 ${S}/include/linux/time.h ${STAGING_INCDIR}/${PN}/linux/
 
# [ -s ${STAGING_INCDIR}/${PN}/asm ] ||
# ln -sf asm-arm ${STAGING_INCDIR}/${PN}/asm
 
# [ -s ${STAGING_INCDIR}/${PN}/asm-arm/arch ] ||
# ln -sf arch-omap3 ${STAGING_INCDIR}/${PN}/asm-arm/arch
}
