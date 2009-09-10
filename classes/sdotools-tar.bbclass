# This is a class for accessing SDO tools internally at TI.

inherit sdotools


# we overide the default do_stage from SDO, since we have a tar ball
# instead of a binary
do_stage() {
    install -d ${SDO_INSTALL_DIR}
    tar -xzf ${DL_DIR}/${SDOFILE} -C ${SDO_INSTALL_DIR}
}




