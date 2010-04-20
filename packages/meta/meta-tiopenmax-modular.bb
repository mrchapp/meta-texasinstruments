DESCRIPTION = "Texas Instruments OpenMAX IL."
PR = "r2"
PROVIDES = "virtual/openmax-il"
RPROVIDES = "virtual/openmax-il"

ALLOW_EMPTY = "1"

RDEPENDS = "\
	tiopenmax-aacdec \
	${@base_contains("DISTRO_FEATURES", "720p", "tiopenmax-aacdec-ittiam", "", d)} \
	tiopenmax-aacenc \
	${@base_contains("DISTRO_FEATURES", "Aricent_720p", "tiopenmax-armaacdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "Aricent_720p", "tiopenmax-armaacenc", "", d)} \
	${@base_contains("DISTRO_FEATURES", "720p", "tiopenmax-videodecitt", "", d)} \
	${@base_contains("DISTRO_FEATURES", "720p", "tiopenmax-videoencitt", "", d)} \
	${@base_contains("DISTRO_FEATURES", "720p", "tiopenmax-mpeg4enc", "", d)} \	
	tiopenmax-audiomanager \
	tiopenmax-avplaytest \
	tiopenmax-avrecordtest \
	${@base_contains("DISTRO_FEATURES", "camera", "tiopenmax-camera", "", d)} \
	tiopenmax-clock \
	tiopenmax-common \
	tiopenmax-core \
	tiopenmax-g711dec \
	tiopenmax-g711enc \
	tiopenmax-gsmfrdec \
	tiopenmax-gsmfrenc \
	tiopenmax-gsmhrdec \
	tiopenmax-gsmhrenc \
	tiopenmax-imaadpcmdec \
	tiopenmax-imaadpcmenc \
	tiopenmax-imagecapturetest \
	tiopenmax-imagedisplaytest \
	tiopenmax-inst2 \
	${@base_contains("DISTRO_FEATURES", "jpegdec", "tiopenmax-jpegdec", "", d)} \
	${@base_contains("DISTRO_FEATURES", "jpegenc", "tiopenmax-jpegenc", "", d)} \
	tiopenmax-lcml \
	tiopenmax-mp3 \
	tiopenmax-nbamrdec \
	tiopenmax-nbamrenc \
	tiopenmax-pcmdec \
	tiopenmax-pcmenc \
	tiopenmax-perf \
	tiopenmax-policymanager \
	tiopenmax-postproc \
	tiopenmax-prepostproc \
	tiopenmax-ram \
	tiopenmax-resourcemanager \
	tiopenmax-rmproxy \
	tiopenmax-videodec \
	tiopenmax-videoenc \
	tiopenmax-videocapturetest \
	tiopenmax-videodisplaytest \
	tiopenmax-wbamrdec \
	tiopenmax-wbamrenc \
	tiopenmax-wmadec \
	"

DEPENDS="${RDEPENDS}"
