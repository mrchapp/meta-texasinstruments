require opkg.inc

PROVIDES += "virtual/update-alternatives"
RPROVIDES_update-alternatives-cworth += "update-alternatives"
RCONFLICTS_update-alternatives-cworth = "update-alternatives-dpkg"
RDEPENDS_${PN} = "${VIRTUAL-RUNTIME_update-alternatives}"
PACKAGE_ARCH_update-alternatives-cworth = "all"

PR = "r9"

PACKAGES =+ "libopkg-dev libopkg update-alternatives-cworth"

FILES_update-alternatives-cworth = "${bindir}/update-alternatives"
FILES_libopkg-dev = "${libdir}/*.a ${libdir}/*.la ${libdir}/*.so"
FILES_libopkg = "${libdir}/*.so.*"

# Define a variable to allow distros to run configure earlier.
# (for example, to enable loading of ethernet kernel modules before networking starts)
OPKG_INIT_POSITION = "98"
OPKG_INIT_POSITION_slugos = "41"

EXTRACFLAGS = ""
EXTRACFLAGS_omap-3430ldp = "-Wno-array-bounds"
EXTRACFLAGS_omap-3430sdp = "-Wno-array-bounds"
EXTRACFLAGS_omap-4430sdp = "-Wno-array-bounds"
EXTRACFLAGS_omap-4430sdphigh = "-Wno-array-bounds"
EXTRACFLAGS_beagleboard = "-Wno-array-bounds"
EXTRACFLAGS_qemuarmv7 = "-Wno-array-bounds"
EXTRACFLAGS_overo = "-Wno-array-bounds"

TARGET_CFLAGS += "${EXTRACFLAGS}"

pkg_postinst_opkg () {
#!/bin/sh
if [ "x$D" != "x" ]; then
	install -d ${IMAGE_ROOTFS}/${sysconfdir}/rcS.d
	# this happens at S98 where our good 'ole packages script used to run
	echo "#!/bin/sh
opkg-cl configure
rm -f /${sysconfdir}/rcS.d/S${OPKG_INIT_POSITION}configure
" > $D${sysconfdir}/rcS.d/S${OPKG_INIT_POSITION}configure
	chmod 0755 $D${sysconfdir}/rcS.d/S${OPKG_INIT_POSITION}configure
fi

update-alternatives --install ${bindir}/opkg opkg ${bindir}/opkg-cl 100
}

pkg_postrm_opkg () {
#!/bin/sh
update-alternatives --remove opkg ${bindir}/opkg-cl
}

