# This is a class for XDC based build
# the following variables can be used when inheriting from this class:
# - Mandatory:
#   - XDCBUILDROOT: path where the top level package.xdc is. The build will
#     take place in this folder, with command xdc -PD .
#
# - Optional:
#   - XDCBUILDCFG: path to the config.bld file used during the build
#   - XDCPATH: to specify extra PATH for the XDC build. Make sure to use
#     XDCPATH += so that you do not overwrite existing paths
#   - XDCARGS: optional args passed to XDC

inherit base

# replace '.' with '_'
def pv_underscore(v):
	import re
	return re.sub("\.", "_", v)

# trim extra white space
def rm_space(v):
	import re
	return re.sub(" ", "", v)

TITOOLS_DIR= ${STAGING_BINDIR}/titools

BIOS_VER   = ${@pv_underscore("${PREFERRED_VERSION_titools-bios}")}
XDC_VER    = ${@pv_underscore("${PREFERRED_VERSION_titools-xdc}")}
FC_VER     = ${@pv_underscore("${PREFERRED_VERSION_titools-fc}")}
CE_VER     = ${@pv_underscore("${PREFERRED_VERSION_titools-ce}")}
OSAL_VER   = ${@pv_underscore("${PREFERRED_VERSION_titools-osal}")}
XDAIS_VER  = ${@pv_underscore("${PREFERRED_VERSION_titools-xdais}")}
IPC_VER    = ${@pv_underscore("${PREFERRED_VERSION_titools-ipc}")}

CGTARM_VER = ${PREFERRED_VERSION_titools-cgtarm}
CGT6X_VER  = ${PREFERRED_VERSION_titools-cgt6x}

XDCPATH += "\
${TITOOLS_DIR}/bios_${BIOS_VER}/packages;\
${TITOOLS_DIR}/framework_components_${FC_VER}/packages;\
${TITOOLS_DIR}/framework_components_${FC_VER}/fctools/packages;\
${TITOOLS_DIR}/osal_${OSAL_VER}/packages;\
${TITOOLS_DIR}/codec_engine_${CE_VER}/packages;\
${TITOOLS_DIR}/xdais_${XDAIS_VER}/packages;\
${TITOOLS_DIR}/ipc_${IPC_VER}/packages;\
"

XDCROOT="${TITOOLS_DIR}/xdctools_${XDC_VER}"

xdc_do_compile() {
    export XDCPATH="${@rm_space("${XDCPATH}")}"
    export XDCROOT="${XDCROOT}"
    export CGT_C64T_PATH="${TITOOLS_DIR}/cgt7x-${CGT6X_VER}"
    export TMS470CGTOOLPATH="${TITOOLS_DIR}/cgtarm-${CGTARM_VER}"
    export XDCBUILDCFG="${XDCBUILDCFG}"
    export XDCARGS="${XDCARGS}"
    cd ${XDCBUILDROOT}
    ${XDCROOT}/xdc ${PARALLEL_MAKE} -PD .
}

EXPORT_FUNCTIONS do_compile
