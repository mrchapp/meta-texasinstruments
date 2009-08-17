DESCRIPTION = "Texas Instruments OpenMAX Code from GIT."
PR = "r0"
PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

# L24.0.10 tag
SRCREV = "L${PV}"

SRC_URI = "\
	git://dev.omapzoom.org/pub/scm/abraham/audio-omx.git;protocol=git \
	"

do_patch(){
	oenote "Patching in: `pwd`"
	sed -i 's#src#src\\\n\ttests#g' git/audio/src/openmax_il/audio_decode/makefile
}

do_stage() {
        chmod -R +w ${WORKDIR}/git/*
        install -d ${STAGING_BINDIR}/audio-omx
        cp -a ${WORKDIR}/git/* ${STAGING_BINDIR}/audio-omx/
}
