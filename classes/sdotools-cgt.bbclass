# This is a class for accessing SDO CGT internally at TI.

inherit native

SERVER="http://syntaxerror.dal.design.ti.com/release/releases"

INSTALL_DIR = ${STAGING_BINDIR}/titools

SRC_URI = "${SERVER}/${SDOPATH}"

# no need to unpack, since we can do it once in the do_stage
do_unpack(){
}

do_stage() {
	cp ${DL_DIR}/${SDOFILE} ${WORKDIR}
    chmod +x ${WORKDIR}/${SDOFILE}
    install -d ${INSTALL_DIR}
    HOME=${S} ${WORKDIR}/${SDOFILE} --mode silent --prefix ${INSTALL_DIR}/${SDONAME}-${PV}
}

PREMIRRORS_prepend () {
ftp://.*/.*    http://pokysources.tif.ti.com/files/
http://.*/.*   http://pokysources.tif.ti.com/files/
https://.*/.*  http://pokysources.tif.ti.com/files/
}
