# tasks and images
PREFERRED_VERSION_task-omap-bridge =			"1.1"
PREFERRED_VERSION_task-omap-connectivity.bb =	        "1.0"
PREFERRED_VERSION_task-omap-gfx.bb =			"1.0"
PREFERRED_VERSION_task-omap-gst.bb =			"1.0"
PREFERRED_VERSION_task-omap-omx.bb =			"1.0"
PREFERRED_VERSION_task-omap-sn =	    		"4430"
PREFERRED_VERSION_task-omap-test.bb =			"1.0"
PREFERRED_VERSION_omap-image-minimal-mtdutils.bb =	"1.0"
PREFERRED_VERSION_omap-image-minimal.bb =		"1.0"
PREFERRED_VERSION_omap-image-mm.bb =			"1.0"
PREFERRED_VERSION_omap-image-sato.bb =			"1.0

# baseport
SRCREV_pn-x-load =		            "X-LOAD_RLS_4.63"
SRCREV_pn-u-boot =		            "ti-omap4-v0.2"
PREFERRED_VERSION_linux-tiomap =	"2.6.31-0.13"

# Device driver test
SRCREV_pn-driver-test = "f90f2da22e5d8bf6b867b5fccfd74c2303990be8"

# Tesla Bridge
SRCREV_pn-tidspbridge-lib                =		"ti-syslink-mpu-rls-24.0.13"
SRCREV_pn-tidspbridge-samples            =		"BRIDGE-DSP_RLS_0.11"
PREFERRED_VERSION_tidspbridge-dllcreate  =	    "24.0.6"

# Ducati syslink
SRCREV_pn-tisyslink-lib              =      "ti-syslink-mpu-rls-24.0.13"
SRCREV_pn-titiler-memmgr             =      "l24.0.13.rel"
SRCREV_pn-titiler-d2cmap             =      "l24.0.13.rel"
SRCREV_pn-tisyslink-ducati           =      "BRIDGE-DUCATI_RLS_0.12-P1"
SRCREV_pn-tisyslink-ducati-mm        =      "TI-MM-DUCATI_RLS.01.08.00.00"

# Tesla/Ducati external tools
PREFERRED_VERSION_titools-cgt6x  =          "7.0.0B2"
PREFERRED_VERSION_titools-cgtarm =          "4.6.0"
PREFERRED_VERSION_titools-bios   =    	    "6.20.02.43"
PREFERRED_VERSION_titools-fc     =          "3.00.00.50.eng"
PREFERRED_VERSION_titools-xdc    =          "3.15.03.67"
PREFERRED_VERSION_titools-ipc    =          "1.00.03.54.eng"
PREFERRED_VERSION_titools-osal   =          "1.00.00.36"
PREFERRED_VERSION_titools-ce     =          "3.00.00.24"
PREFERRED_VERSION_titools-xdais  =          "7.00.00.18.eng"

# baseimage
SRCREV_pn-baseimage ?=				"DSP-DASF-SYSTEM-TESLA_RLS_4.3"
PREFERRED_VERSION_baseimage-masterconfig =	"23.x"

# socket nodes and codecs
PREFERRED_VERSION_tisocketnode-algo =			"3.02"
PREFERRED_VERSION_tisocketnode-dfgm =			"3.02"
SRCREV_pn-tisocketnode-usn =				"DSP-MM-TESLA_RLS_4.3-P1"
PREFERRED_VERSION_tisocketnode-control-task =		"3.40"
PREFERRED_VERSION_tisocketnode-ringio =			"3.02"
#
PREFERRED_VERSION_tisocketnode-g711dec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g711dec-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g711enc =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g711enc-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g722dec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g722dec-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g722enc =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g722enc-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g723dec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g723dec-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g723enc =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g723enc-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g726dec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g726dec-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g726enc =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g726enc-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g729dec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g729dec-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g729enc =		"3.17.1"
PREFERRED_VERSION_tisocketnode-g729enc-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-gsmfrdec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-gsmfrdec-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-gsmfrenc =		"3.17.1"
PREFERRED_VERSION_tisocketnode-gsmfrenc-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-gsmhrdec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-gsmhrdec-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-gsmhrenc =		"3.17.1"
PREFERRED_VERSION_tisocketnode-gsmhrenc-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-ilbcdec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-ilbcdec-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-ilbcenc =		"3.17.1"
PREFERRED_VERSION_tisocketnode-ilbcenc-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-ima-adpcmdec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-ima-adpcmdec-code =	"3.17.1"	
PREFERRED_VERSION_tisocketnode-ima-adpcmenc =		"3.17.1"
PREFERRED_VERSION_tisocketnode-ima-adpcmenc-codec =	"3.17.1"
SRCREV_pn-tisocketnode-mp3               =              "DSP-MM-TESLA_RLS_4.3-P1"
SRCREV_pn-tisocketnode-mp3-codec         =              "DSP-MM-TESLA_RLS_4.3-P1"
SRCREV_pn-tisocketnode-mpeg4aacdec       =              "DSP-MM-TESLA_RLS_4.3-P1"
SRCREV_pn-tisocketnode-mpeg4aacdec-codec =              "DSP-MM-TESLA_RLS_4.3-P1"
SRCREV_pn-tisocketnode-mpeg4aacenc       =              "DSP-MM-TESLA_RLS_4.3-P1"
SRCREV_pn-tisocketnode-mpeg4aacenc-codec =              "DSP-MM-TESLA_RLS_4.3-P1"
SRCREV_pn-tisocketnode-nbamrdec          =              "DSP-MM-TESLA_RLS_4.3-P1"
SRCREV_pn-tisocketnode-nbamrdec-codec    =              "DSP-MM-TESLA_RLS_4.3-P1"
SRCREV_pn-tisocketnode-nbamrenc          =              "DSP-MM-TESLA_RLS_4.3-P1"
SRCREV_pn-tisocketnode-nbamrenc-codec    =              "DSP-MM-TESLA_RLS_4.3-P1"
PREFERRED_VERSION_tisocketnode-nmu-plc-vad =		"3.02"
PREFERRED_VERSION_tisocketnode-pcmdec =			"3.17.1"
PREFERRED_VERSION_tisocketnode-pcmenc =			"3.17.1"
PREFERRED_VERSION_tisocketnode-wbamrdec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-wbamrdec-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-wbamrenc =		"3.17.1"
PREFERRED_VERSION_tisocketnode-wbamrenc-codec =		"3.17.1"
PREFERRED_VERSION_tisocketnode-wma9 =			"3.17.1"
PREFERRED_VERSION_tisocketnode-wma9-codec =		"3.17.1"
#
PREFERRED_VERSION_tisocketnode-jpegdec =		"3.16"
PREFERRED_VERSION_tisocketnode-jpegdec-codec =		"3.16"
PREFERRED_VERSION_tisocketnode-jpegenc =		"4.13"
PREFERRED_VERSION_tisocketnode-jpegenc-codec =		"4.13"
#
PREFERRED_VERSION_tisocketnode-vpp =			"3.16"
PREFERRED_VERSION_tisocketnode-wmv9 =			"3.16"
PREFERRED_VERSION_tisocketnode-wmv9-codec =		"3.16"
PREFERRED_VERSION_tisocketnode-ipp =			"4.13"
PREFERRED_VERSION_tisocketnode-h264dec =		"4.13"
PREFERRED_VERSION_tisocketnode-h264dec-codec =		"4.13"
PREFERRED_VERSION_tisocketnode-h264enc =		"4.13"
PREFERRED_VERSION_tisocketnode-h264enc-codec =		"4.13"
PREFERRED_VERSION_tisocketnode-mpeg2 =			"4.13"
PREFERRED_VERSION_tisocketnode-mpeg2-codec =		"4.13"
PREFERRED_VERSION_tisocketnode-mpeg4dec =		"4.13"
PREFERRED_VERSION_tisocketnode-mpeg4dec-codec =		"4.13"
PREFERRED_VERSION_tisocketnode-mpeg4enc =		"4.13"
PREFERRED_VERSION_tisocketnode-mpeg4enc-codec =		"4.13"
PREFERRED_VERSION_tisocketnode-spark =			"4.13"
PREFERRED_VERSION_tisocketnode-spark-codec =		"4.13"
PREFERRED_VERSION_tisocketnode-mpeg4-720pdec =		"4.13"
PREFERRED_VERSION_tisocketnode-mpeg4-720pdec-codec =	"4.13"


# TI openmax
PREFERRED_PROVIDER_virtual/openmax-il =	"meta-tiopenmax-modular"
SRCREV_pn-tiopenmax-audiodecode       = "L24.0.13"
SRCREV_pn-tiopenmax-audioencode       = "L24.0.13"
SRCREV_pn-tiopenmax-base              = "L24.0.13"
SRCREV_pn-tiopenmax-core              = "L24.0.13"
SRCREV_pn-tiopenmax-lcml              = "L24.0.13"
SRCREV_pn-tiopenmax-osal              = "L24.0.13"

# gstreamer
PREFERRED_VERSION_libgoo =		"5.12"
PREFERRED_VERSION_gst-goo =		"5.12-P1"
PREFERRED_VERSION_gst-pyapps =		"5.11"

# connectivity
PREFERRED_VERSION_wilink = 		"23-1.2-P1"
PREFERRED_VERSION_btfm = 		"23-1.2"

# graphics
SRCREV_pn-sgx-kernel-module =	"GFX-LINUX-SGX-DDK-L24_RLS_0.1"
SRCREV_pn-sgx-lib-noxws =	"GFX-LINUX-SGX-DDK-L24_RLS_0.1"

# open source dependencies
PREFERRED_VERSION_gst-plugins-bad =	"0.10.8"
PREFERRED_VERSION_gst-plugins-ugly =	"0.10.9"
PREFERRED_VERSION_gst-plugins-good =	"0.10.7"
PREFERRED_VERSION_gst-qtmux =		"svn"
PREFERRED_VERSION_check =		"0.9.5"
PREFERRED_VERSION_amrnb =		"7.0.0.2"
PREFERRED_VERSION_amrwb =		"7.0.0.1"
#PREFERRED_VERSION_openobex =		"0.22"
PREFERRED_VERSION_bison-1.875 =		"1.875"
PREFERRED_VERSION_bison-native-1.875 =	"1.875"
PREFERRED_VERSION_flex =		"2.5.35"
PREFERRED_VERSION_flex-native =		"2.5.35"
PREFERRED_VERSION_iperf =		"2.0.4"
PREFERRED_VERSION_libmms =		"0.3"

# toolchain
PREFERRED_VERSION_external-csl-toolchain =	"2008q3-72"

