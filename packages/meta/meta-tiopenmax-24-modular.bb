DESCRIPTION = "Texas Instruments OpenMAX IL."
PR = "r4"
PROVIDES = "virtual/openmax-il"
RPROVIDES = "virtual/openmax-il"

ALLOW_EMPTY = "1"

RDEPENDS = "\
	tiopenmax-audiodecode \
	tiopenmax-audioencode \
	tiopenmax-base \
	tiopenmax-core \
	tiopenmax-lcml \
	tiopenmax-osal \
	tiopenmax-rmproxy \
	tiopenmax-videodec \
	tiopenmax-videoenc \
	tiopenmax-stubcamera \
	"

DEPENDS="${RDEPENDS}"
