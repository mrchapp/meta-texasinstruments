DESCRIPTION = "Texas Instruments OpenMAX IL."
PR = "r3"
PROVIDES = "virtual/openmax-il"
RPROVIDES = "virtual/openmax-il"

ALLOW_EMPTY = "1"

RDEPENDS = "\
	tiopenmax-core \
	tiopenmax-common \
	tiopenmax-clock \
	tiopenmax-lcml \
	\
	${@base_contains("DISTRO_FEATURES", "opensource", "", "tiopenmax-perf", d)} \
	${@base_contains("DISTRO_FEATURES", "opensource", "", "tiopenmax-audiomanager", d)} \
	${@base_contains("DISTRO_FEATURES", "opensource", "", "tiopenmax-policymanager", d)} \
	${@base_contains("DISTRO_FEATURES", "opensource", "", "tiopenmax-resourcemanager", d)} \
	${@base_contains("DISTRO_FEATURES", "opensource", "", "tiopenmax-rmproxy", d)} \
	${@base_contains("DISTRO_FEATURES", "opensource", "", "tiopenmax-ram", d)} \
	\
	${@base_contains("DISTRO_FEATURES", "videodec", "tiopenmax-videodec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "videoenc", "tiopenmax-videoenc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "postproc", "tiopenmax-postproc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "prepostproc", "tiopenmax-prepostproc", "", d)} \
	\
	${@base_contains("DISTRO_FEATURES", "aacdec", "tiopenmax-aacdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "aacenc", "tiopenmax-aacenc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "720p", "tiopenmax-armaacdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "720p", "tiopenmax-armaacenc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "camera", "tiopenmax-camera", "", d)} \
	${@base_contains("DISTRO_FEATURES", "g711dec", "tiopenmax-g711dec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "g711enc", "tiopenmax-g711enc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "g722dec", "tiopenmax-g722dec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "g722enc", "tiopenmax-g722enc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "g723dec", "tiopenmax-g723dec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "g723enc", "tiopenmax-g723enc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "g726dec", "tiopenmax-g726dec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "g726enc", "tiopenmax-g726enc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "g729dec", "tiopenmax-g729dec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "g729enc", "tiopenmax-g729enc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "gsmfrdec", "tiopenmax-gsmfrdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "gsmfrenc", "tiopenmax-gsmfrenc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "gsmhrdec", "tiopenmax-gsmhrdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "gsmhrenc", "tiopenmax-gsmhrenc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "ilbcdec", "tiopenmax-ilbcdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "ilbcenc", "tiopenmax-ilbcenc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "imaadpcmdec", "tiopenmax-imaadpcmdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "imaadpcmenc", "tiopenmax-imaadpcmenc", "", d)} \
	\
	${@base_contains("DISTRO_FEATURES", "mp3dec", "tiopenmax-mp3", "", d)} \
	${@base_contains("DISTRO_FEATURES", "nbamrdec", "tiopenmax-nbamrdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "nbamrenc", "tiopenmax-nbamrenc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "pcmdec", "tiopenmax-pcmdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "pcmenc", "tiopenmax-pcmenc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "rarv", "tiopenmax-rageckodec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "wbamrdec", "tiopenmax-wbamrdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "wbamrenc", "tiopenmax-wbamrenc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "wmadec", "tiopenmax-wmadec", "", d)} \
	\
	${@base_contains("DISTRO_FEATURES", "jpegdec", "tiopenmax-jpegdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "jpegenc", "tiopenmax-jpegenc", "", d)} \
	\
	${@base_contains("DISTRO_FEATURES", "opensource", "", "tiopenmax-avplaytest", d)} \
	${@base_contains("DISTRO_FEATURES", "opensource", "", "tiopenmax-avrecordtest", d)} \
	${@base_contains("DISTRO_FEATURES", "opensource", "", "tiopenmax-imagecapturetest", d)} \
	${@base_contains("DISTRO_FEATURES", "opensource", "", "tiopenmax-imagedisplaytest", d)} \
	${@base_contains("DISTRO_FEATURES", "opensource", "", "tiopenmax-videocapturetest", d)} \
	${@base_contains("DISTRO_FEATURES", "opensource", "", "tiopenmax-videodisplaytest", d)} \
	"

DEPENDS="${RDEPENDS}"
