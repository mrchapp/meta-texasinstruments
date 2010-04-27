
# baseport
SRCREV_pn-x-load  =                 "L24.5"
SRCREV_pn-u-boot =                  "L24.5"
SRCREV_pn-linux-tiomap =            "ti-2.6.33-rc2-omap4-L24.5-p2"

# Device driver test
SRCREV_pn-device-driver-test =      "L24.5"

# Tesla Bridge
#SRCREV_pn-tidspbridge-lib            =      "ti-syslink-mpu-rls-24.4"
#SRCREV_pn-tidspbridge-tesla          =      "BRIDGE-DSP_RLS_0.13"
#SRCREV_pn-tidspbridge-dllcreate      =      "DYN-LOADER_RLS_1.72"

# Ducati syslink
SRCREV_pn-tisyslink-lib              =      "ti-syslink-mpu-rls-24.5-p1"
SRCREV_pn-tisyslink-d2cmap           =      "ti-syslink-mpu-rls-24.5-p1"
SRCREV_pn-tisyslink-ducati           =      "BRIDGE-DUCATI_RLS_0.17"
SRCREV_pn-tisyslink-ducati-mm        =      "TI-MM-DUCATI_RLS.01.13.00.00-P1"
SRCREV_pn-titiler-memmgr             =      "l24.5"

# Tesla/Ducati external tools
PREFERRED_VERSION_titools-cgt6x  =          "7.0.0B4"
PREFERRED_VERSION_titools-cgtarm =          "4.6.0"
PREFERRED_VERSION_titools-bios   =          "6.21.02.18"
PREFERRED_VERSION_titools-fc     =          "3.00.01.60"
PREFERRED_VERSION_titools-xdc    =          "3.16.02.32"
PREFERRED_VERSION_titools-ipc    =          "1.00.03.54.eng"
PREFERRED_VERSION_titools-osal   =          "1.00.00.41"
PREFERRED_VERSION_titools-ce     =          "3.00.00.31"
PREFERRED_VERSION_titools-xdais  =          "7.00.00.27"

# baseimage
#SRCREV_pn-baseimage ?=                      "DSP-DASF-SYSTEM-TESLA_RLS_4.4"
#PREFERRED_VERSION_baseimage-masterconfig =  "23.x"

# socket nodes and codecs
#SRCREV_pn-tisocketnode-usn =                        "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-mp3               =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-mp3-codec         =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-mpeg4aacdec       =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-mpeg4aacdec-codec =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-mpeg4aacenc       =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-mpeg4aacenc-codec =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-nbamrdec          =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-nbamrdec-codec    =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-nbamrenc          =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-nbamrenc-codec    =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-wbamrdec          =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-wbamrdec-codec    =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-wbamrenc          =          "DSP-MM-TESLA_RLS_4.6"
#SRCREV_pn-tisocketnode-wbamrenc-codec    =          "DSP-MM-TESLA_RLS_4.6"

# TI openmax
#PREFERRED_PROVIDER_virtual/openmax-il = "meta-tiopenmax-modular"
#SRCREV_pn-tiopenmax-audiodecode       = "L24.4-p2"
#SRCREV_pn-tiopenmax-audioencode       = "L24.4-p2"
#SRCREV_pn-tiopenmax-base              = "L24.4-p2"
#SRCREV_pn-tiopenmax-lcml              = "L24.4-p2"
SRCREV_pn-tiopenmax-core              = "L24.5"
SRCREV_pn-tiopenmax-osal              = "L24.5"
SRCREV_pn-tiopenmax-domx              = "L24.5"

# connectivity
SRCREV_pn-wilink =                      "ti-wifi-omap4-L24.4"
SRCREV_pn-wilink-firmware =             "LINUX-WCG_RLS_24.2"

# graphics
SRCREV_pn-sgx-kernel-module =           "GFX-LINUX-SGX-DDK-L24_RLS_1.4.1"
SRCREV_pn-sgx-lib-noxws =               "GFX-LINUX-SGX-DDK-L24_RLS_1.4.1"
SRCREV_pn-sgx-sdk =                     "GFX-LINUX-SGX-SDK-L24_RLS_2.5.3"
SRCREV_pn-sgx-benchmark =               "GFX-LINUX-SGX-BP-L24_RLS_2.5.3"
SRCREV_pn-gfx-ticpublt =                "OMAP_LINUX_TICPUBLT_BIN_RLS_1.0.0.0"

# open source dependencies

# ALSA
PREFERRED_VERSION_alsa-lib =            "1.0.18"
PREFERRED_VERSION_alsa-utils =          "1.0.18"

# gstreamer
SRCREV_pn-gst-openmax =                 "L24.5"
SRCREV_pn-gst-plugin-bc =               "L24.5"
SRCREV_pn-gst-plugins-bad =             "L24.5"
SRCREV_pn-gst-plugins-ugly =            "L24.5"
SRCREV_pn-gst-plugins-good =            "L24.5"
SRCREV_pn-gst-plugins-base =            "L24.5"
SRCREV_pn-gstreamer =                   "L24.5"
SRCREV_pn-gst-plugin-h264 =             "3a3f90f77cd1afd340b46f441a40d8d635f1a2ee"
PREFERRED_VERSION_gst-pyapps =          "5.11"
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
PREFERRED_VERSION_pixman    =           "0.17.4"
PREFERRED_VERSION_gtk+ =                "2.14.7"
SRCREV_pn-webkit-gtk =                  "50081"

# tools
PREFERRED_VERSION_htop =                 "0.7"

# toolchain
PREFERRED_VERSION_external-csl-toolchain = "2009q1-203"

# xserver
PREFERRED_VERSION_xserver-xf86-lite = "1.7.4"

