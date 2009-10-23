# This is a class for accessing SDO tools internally at TI.

inherit native

SDOSERVER="http://www.sanb.design.ti.com/tisb_releases"

SDO_INSTALL_DIR = ${STAGING_BINDIR}/titools

SRC_URI = "${SDOSERVER}/${SDOPATH}"

# no need to unpack, since we can do it once in the do_stage
do_unpack(){
}

do_stage() {
    chmod +x ${DL_DIR}/${SDOFILE}
    install -d ${SDO_INSTALL_DIR}
    HOME=${S} ${DL_DIR}/${SDOFILE} --mode silent --prefix ${SDO_INSTALL_DIR}
}

PREMIRRORS_prepend () {
ftp://.*/.*    http://pokysources.tif.ti.com/files/
http://.*/.*   http://pokysources.tif.ti.com/files/
https://.*/.*  http://pokysources.tif.ti.com/files/
}
