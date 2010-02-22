
# baseport
SRCREV_pn-x-load  =                 "L24.4"
SRCREV_pn-u-boot =                  "L24.4"
SRCREV_pn-linux-tiomap =            "58b3048ba73662bf898aba594da03b5d403e0614"

# Device driver test
SRCREV_pn-device-driver-test =      "d8a8672a4e12f0ad8ae1134273405d240ce5b19b"

# Tesla Bridge
SRCREV_pn-tidspbridge-lib            =      "ti-syslink-mpu-rls-24.4"
SRCREV_pn-tidspbridge-tesla          =      "BRIDGE-DSP_RLS_0.13"
SRCREV_pn-tidspbridge-dllcreate      =      "DYN-LOADER_RLS_1.72"

# Ducati syslink
SRCREV_pn-tisyslink-lib              =      "ti-syslink-mpu-rls-24.4"
SRCREV_pn-titiler-memmgr             =      "l24.4.prerel"
SRCREV_pn-titiler-d2cmap             =      "l24.4.prerel"
SRCREV_pn-tisyslink-ducati           =      "BRIDGE-DUCATI_RLS_0.16"
SRCREV_pn-tisyslink-ducati-mm        =      "TI-MM-DUCATI_RLS.01.11.00.00-P1"

# Tesla/Ducati external tools
PREFERRED_VERSION_titools-cgt6x  =          "7.0.0B4"
PREFERRED_VERSION_titools-cgtarm =          "4.6.0"
PREFERRED_VERSION_titools-bios   =          "6.20.03.44.eng"
PREFERRED_VERSION_titools-fc     =          "3.00.00.50.eng"
PREFERRED_VERSION_titools-xdc    =          "3.15.03.67"
PREFERRED_VERSION_titools-ipc    =          "1.00.03.54.eng"
PREFERRED_VERSION_titools-osal   =          "1.00.00.38"
PREFERRED_VERSION_titools-ce     =          "3.00.00.26"
PREFERRED_VERSION_titools-xdais  =          "7.00.00.20.eng"

# baseimage
SRCREV_pn-baseimage ?=                      "DSP-DASF-SYSTEM-TESLA_RLS_4.4"
PREFERRED_VERSION_baseimage-masterconfig =  "23.x"

# socket nodes and codecs
SRCREV_pn-tisocketnode-usn =                        "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-mp3               =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-mp3-codec         =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-mpeg4aacdec       =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-mpeg4aacdec-codec =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-mpeg4aacenc       =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-mpeg4aacenc-codec =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-nbamrdec          =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-nbamrdec-codec    =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-nbamrenc          =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-nbamrenc-codec    =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-wbamrdec          =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-wbamrdec-codec    =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-wbamrenc          =          "DSP-MM-TESLA_RLS_4.6"
SRCREV_pn-tisocketnode-wbamrenc-codec    =          "DSP-MM-TESLA_RLS_4.6"

# TI openmax
PREFERRED_PROVIDER_virtual/openmax-il = "meta-tiopenmax-modular"
SRCREV_pn-tiopenmax-audiodecode       = "L24.4"
SRCREV_pn-tiopenmax-audioencode       = "L24.4"
SRCREV_pn-tiopenmax-base              = "L24.4"
SRCREV_pn-tiopenmax-lcml              = "L24.4"
SRCREV_pn-tiopenmax-core              = "L24.4"
SRCREV_pn-tiopenmax-osal              = "L24.4"
SRCREV_pn-tiopenmax-domx              = "L24.4"

# connectivity
SRCREV_pn-wilink =                      "ti-wifi-omap4-L24.4"
SRCREV_pn-wilink-firmware =             "LINUX-WCG_RLS_24.2"

# graphics
SRCREV_pn-sgx-kernel-module =           "GFX-LINUX-SGX-DDK-L24_RLS_1.3.0"
SRCREV_pn-sgx-lib-noxws =               "GFX-LINUX-SGX-DDK-L24_RLS_1.3.0"
SRCREV_pn-sgx-sdk =                     "GFX-LINUX-SGX-SDK-L24_RLS_2.5.2"
SRCREV_pn-sgx-benchmark =               "GFX-LINUX-SGX-BP-L24_RLS_2.5.2"
SRCREV_pn-gfx-ticpublt =                "OMAP_LINUX_TICPUBLT_BIN_RLS_1.0.0.0"

# open source dependencies

# ALSA
PREFERRED_VERSION_alsa-lib =            "1.0.18"
PREFERRED_VERSION_alsa-utils =          "1.0.18"

# gstreamer
PREFERRED_VERSION_gst-pyapps =          "5.11"
PREFERRED_VERSION_gst-plugins-bad =     "git"
PREFERRED_VERSION_gst-plugins-ugly =    "git"
PREFERRED_VERSION_gst-plugins-good =    "git"
PREFERRED_VERSION_gst-plugins-base =    "git"
PREFERRED_VERSION_gstreamer =           "git"
PREFERRED_VERSION_gst-qtmux =           "svn"
PREFERRED_VERSION_check =               "0.9.5"
PREFERRED_VERSION_amrnb =               "7.0.0.2"
PREFERRED_VERSION_amrwb =               "7.0.0.1"
#PREFERRED_VERSION_openobex =           "0.22"
PREFERRED_VERSION_htop =                "0.7"
PREFERRED_VERSION_bison =               "2.3"
PREFERRED_VERSION_bison-native =        "2.3"
PREFERRED_VERSION_flex =                "2.5.35"
PREFERRED_VERSION_flex-native =         "2.5.35"
PREFERRED_VERSION_iperf =               "2.0.4"
PREFERRED_VERSION_libmms =              "0.3"

PREFERRED_VERSION_glib-2.0    =         "2.22.1"
PREFERRED_VERSION_glib-2.0-native =     "2.22.1"
PREFERRED_VERSION_pango        =        "1.22.4"
PREFERRED_VERSION_cairo        =        "1.8.6"
PREFERRED_VERSION_pixman    =           "0.14.0"
PREFERRED_VERSION_gtk+ =                "2.14.7"
SRCREV_pn-webkit-gtk =                  "50081"

# tools
PREFERRED_VERSION_htop =                 "0.7"

# toolchain
PREFERRED_VERSION_external-csl-toolchain = "2009q1-203"

# xserver
SRCREV_pn-xf86-video-omapfb               = "5317aae587a2bf48f07a8c06bfaf7bcbfd23bafc"
PREFERRED_VERSION_xserver-xf86-lite = "1.6.1"

