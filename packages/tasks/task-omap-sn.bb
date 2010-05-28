#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for TI's Socket Nodes"
PR = "r2"

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS = "\
    tisocketnode-control-task \
    tisocketnode-dfgm \
    tisocketnode-ipp \
    tisocketnode-ringio \
    tisocketnode-usn \
    tisocketnode-conversions \
    tisocketnode-pplib-dualout\
    tisocketnode-g711dec \
    tisocketnode-g711enc \
    tisocketnode-gsmfrdec \
    tisocketnode-gsmfrenc \
    tisocketnode-gsmhrdec \
    tisocketnode-gsmhrenc \
    tisocketnode-ima-adpcmdec \
    tisocketnode-ima-adpcmenc \
    tisocketnode-mp3 \
    tisocketnode-mpeg4aacdec \
    tisocketnode-mpeg4aacenc \
    tisocketnode-nbamrdec \
    tisocketnode-nbamrenc \
    tisocketnode-pcmdec \
    tisocketnode-pcmenc \
    tisocketnode-wbamrdec \
    tisocketnode-wbamrenc \
    tisocketnode-wma9 \
    \
    tisocketnode-h264dec \
    tisocketnode-h264enc \
    tisocketnode-mpeg4dec \
    tisocketnode-mpeg4enc \
    ${@base_contains("DISTRO_FEATURES", "spark", "tisocketnode-spark", "", d)} \    
    tisocketnode-vpp \
    tisocketnode-wmv9 \
    ${@base_contains("DISTRO_FEATURES", "Aricent_720p", "tisocketnode-mpeg4-720pdec", "", d)} \
    ${@base_contains("DISTRO_FEATURES", "Aricent_720p", "tisocketnode-mpeg4-720penc", "", d)} \
    ${@base_contains("DISTRO_FEATURES", "720p", "tisocketnode-h264decitt", "", d)} \
    ${@base_contains("DISTRO_FEATURES", "720p", "tisocketnode-mpeg4decitt", "", d)} \
    ${@base_contains("DISTRO_FEATURES", "720p", "tisocketnode-mpeg4encitt", "", d)} \
    \
    tisocketnode-jpegdec \
    tisocketnode-jpegenc \
    "

DEPENDS = "\
    tisocketnode-algo \
    tisocketnode-nmu-plc-vad \
    "
