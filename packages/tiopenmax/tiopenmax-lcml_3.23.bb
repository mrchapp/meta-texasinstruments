DEPENDS = "tidspbridge-lib tiopenmax-core"
DESCRIPTION = "Texas Instruments Linux Common Media Library for OpenMAX IL."
PR = "r1"
COMPONENT_PATH = "system/src/openmax_il/lcml"

require tiopenmax-modular.inc
require tiopenmax-cspec-${PV}.inc
inherit pkgconfig

OMX_COMPILE_TARGET = "lcml"
OMX_INSTALL_TARGET = "lcml.install"
OMX_BUILD_DIR = ""

SRC_URI = "file://23.11-lcmlnocore.patch;patch=1 file://libLCML.pc"

do_compile_prepend() {
	install -m 0644 ${FILESDIR}/libLCML.pc ${S}/libLCML.pc
}
