DESCRIPTION = "Texas Instruments MPU/DSP Bridge libraries."
PR = "r1"
DEPENDS = "linux-tiomap"

inherit update-rc.d

PACKAGES = "${PN}"

SRC_URI = "\
	file://install_bridge \
	file://install_bridge_128 \
	file://uninstall_bridge \
	file://cexec.out \
	"

INITSCRIPT_NAME = "bridge"
INITSCRIPT_PARAMS = "start 01 5 ."


do_install() {
	install -d ${D}/dspbridge
	install -m 0755 ${FILESDIR}/{install_bridge,install_bridge_128,uninstall_bridge,cexec.out} ${D}/dspbridge
	install -d ${D}${sysconfdir}/init.d
	#install -m 0755 ${FILESDIR}/bridge.init ${D}${sysconfdir}/init.d/bridge
        # setup defaults:
#
# Defaults for /etc/init.d/bridge
#
#DEFAULT_BASEIMAGE=/lib/dsp/baseimage.dof

# for OMXResourceManager (also started by /etc/init.d/bridge):
#export QOSDYN_FILE=/dspbridge/qosdyn_4430.dll64T

# for OMXPolicyManager (also started by /etc/init.d/bridge):
#export PM_TBLFILE=/omx/policytable.tbl

# for OMXAudioManager (also started by /etc/init.d/bridge):
#export DCTN_DLLFILE=/lib/dsp/dctn_dyn.dll64T

#EOF

}

#FILES_${PN} = "/dspbridge ${sysconfdir}/init.d/bridge ${sysconfdir}/default/bridge "
