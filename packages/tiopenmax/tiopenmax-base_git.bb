DESCRIPTION = "Texas Instruments OpenMAX IL Base Component."
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

PR = "r2"

require tiopenmax-audio-git.inc

DEPENDS = "tiopenmax-osal tiopenmax-core"
inherit pkgconfig autotools

S = "${WORKDIR}/git/system/omx_base/"

do_stage() {
    autotools_stage_all
}
