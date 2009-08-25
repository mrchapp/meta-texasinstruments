DESCRIPTION = "Texas Instruments OpenMAX IL."
PR = "r3"
PROVIDES = "virtual/openmax-il"
RPROVIDES = "virtual/openmax-il"

ALLOW_EMPTY = "1"

RDEPENDS = "\
	tiopenmax-audiodecode \
	tiopenmax-base \
	tiopenmax-core \
	tiopenmax-lcml \
	tiopenmax-osal \
	tiopenmax-rmproxy \
	tiopenmax-videodec \
	tiopenmax-videoenc \
	"

DEPENDS="${RDEPENDS}"
