require gst-plugins-git.inc
DEPENDS = "gst-plugins-base sgx-kernel-module sgx-lib-noxws virtual/kernel" 
PR = "r5"

SRC_URI += "git://dev.omapzoom.org/pub/scm/gstreamer/gst-plugin-bc.git;protocol=git"
SRC_URI += "file://module-makefile.patch;patch=1"
S = "${WORKDIR}/git"

inherit autotools pkgconfig
    
PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_${PN} += "${libdir}/gstreamer-0.10/libgst*.so /lib/modules/*" 
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgst*.*a "
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/ /lib/modules/.debug/*"

export ARCH=arm
export INSTALL_MOD_PATH=${D}

EXTRA_OECONF = "--enable-gles1-example --enable-gles2-example --with-kpath=${STAGING_KERNEL_DIR} --with-gsdk=${STAGING_INCDIR}"

do_compile_prepend() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
}

