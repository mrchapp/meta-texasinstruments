DESCRIPTION = "Texas Instruments OpenMAX IL Linux Common Media Library (LCML)."
PRIORITY = "optional"
LICENSE = "LGPL"
SECTION = "libs"

PR = "r2"

require tiopenmax-audio-git.inc

DEPENDS = "tiopenmax-osal tiopenmax-core tidspbridge-lib"
inherit pkgconfig autotools

S = "${WORKDIR}/git/system/lcml/"

do_stage() {
    autotools_stage_all
}

