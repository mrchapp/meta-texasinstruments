DEPENDS = "gst-plugins-base virtual/openmax-il"
PR = "r3"
 
SRC_URI = "git://github.com/roopar/gst-openmax.git;protocol=git;branch=omap \
	file://omxincpath.patch;patch=1"
# From omap branch:
SRCREV = "5e0eaa106042238174c2c6bb09374636425f3bb8"
S = "${WORKDIR}/git"
 
inherit autotools
   
EXTRA_OECONF += "--disable-valgrind"
    
do_patch2() {
     echo ${SRCREV} > ${S}/.version
}
       
FILES_${PN} += "${libdir}/gstreamer-0.10/libgstomx.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/libgstomx.*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug/"
       
addtask patch2 after do_patch before do_configure
