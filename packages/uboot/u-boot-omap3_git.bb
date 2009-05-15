require u-boot.inc

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-omap3-git/${MACHINE}"

SRCREV = "4025cfbde3611b14c0d4831a5524e5e061128e30"

PV = "2009.03+${PR}+git${SRCREV}"
PR="r0"

SRC_URI = "git://gitorious.org/u-boot-omap3/mainline.git;branch=omap3-dev;protocol=git \
          "

UBOOT_MACHINE_beagleboard = "omap3_beagle_config"
UBOOT_MACHINE_omap3evm = "omap3_evm_config"
UBOOT_MACHINE_overo = "omap3_overo_config"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(beagleboard|omap3evm|overo)"
