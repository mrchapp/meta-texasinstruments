DESCRIPTION = "A Client for Wi-Fi Protected Access (WPA)."
SECTION = "network"
LICENSE = "GPL"
HOMEPAGE = "http://hostap.epitest.fi/wpa_supplicant/"
DEPENDS = "gnutls \
${@base_contains("COMBINED_FEATURES", "madwifi", "madwifi-ng", "",d)} \
${@base_contains("DISTRO_FEATURES", "wapi", " wapi", "", d)} \
openssl \
"


PR = "r5"

#we introduce MY_ARCH to get 'armv5te' as arch instead of the misleading 'arm' on armv5te builds
MY_ARCH := "${PACKAGE_ARCH}"
PACKAGE_ARCH = "${@base_contains('COMBINED_FEATURES', 'madwifi', '${MACHINE_ARCH}', '${MY_ARCH}', d)}"

SRC_URI = "http://hostap.epitest.fi/releases/wpa_supplicant-${PV}.tar.gz \
	file://defconfig-openssl-wsc \
	file://ifupdown.sh \
	file://functions.sh \
	file://add-tempo-init.patch;patch=1 \
	file://wsc-support.patch;patch=1 \
	file://wsc_supplicant.c \
	file://wsc_supplicant.h \
	file://WscTypes.h \
	file://eap_wsc.c \
	file://CipherWrapper.c \
	file://CipherWrapper.h \
	file://bufferObj.c \
	file://bufferObj.h \
	file://TI_IPC_Api.h \	
	"

S = "${WORKDIR}/wpa_supplicant-${PV}"

export TI_WAPI = "${@base_contains("DISTRO_FEATURES", "wapi", "1", "0", d)}"

SRC_WSC = "\
 wsc_supplicant.c \
 wsc_supplicant.h \
 WscTypes.h \
 eap_wsc.c \
 CipherWrapper.c \
 CipherWrapper.h \
 bufferObj.c \
 bufferObj.h \
 TI_IPC_Api.h \
 "

SRC_WAPI = "\
  ec.c \
  ec.h \
  gem.c \
  gem.h \
  wapi.c \
  wapi.h \
"

PACKAGES_prepend = "wpa-supplicant-passphrase "
FILES_wpa-supplicant-passphrase = "/usr/sbin/wpa_passphrase"

RREPLACES = "wpa-supplicant-cli"

RRECOMMENDS_${PN} = "wpa-supplicant-passphrase"

export HAS_MADWIFI = "${@base_contains('COMBINED_FEATURES', 'madwifi', 1, 0,d)}"

do_configure () {
        install -m 0755 ${WORKDIR}/defconfig-openssl-wsc  .config
	for i in ${SRC_WSC} ; do
		install -m 0644 ${WORKDIR}/$i $i
	done
        if [ "x$HAS_MADWIFI" == "x1" ] ; then
                echo "CONFIG_DRIVER_MADWIFI=y" >> .config
                echo "CFLAGS += -I${STAGING_INCDIR}/madwifi-ng" >> .config
        fi
        if [ "x${TI_WAPI}" == "x1" ] && [ "${MACHINE_ARCH}" == "zoom3" ] ; then
	  echo "TI_WAPI enabled"
	  patch -p1 < ${STAGING_DIR_TARGET}/WAPI/wapi.patch
          for i in ${SRC_WAPI} ; do
            install -m 0644 ${STAGING_DIR_TARGET}/WAPI/$i $i
          done
	  echo "CONFIG_WAPI=y" >> .config
          echo "CONFIG_INTERNAL_LIBTOMMATH=y" >> .config
	fi
}

do_compile () {
	make
}

do_stage() {
	install -d ${STAGING_INCDIR}/wpa-supplicant
	echo "Staging dir: ${STAGING_INCDIR}/wpa-supplicant"
	for i in `ls ${S}/*.h ${S}/wpa_gui/*.h`; do
		install -m 0644 $i ${STAGING_INCDIR}/wpa-supplicant
	done
}

do_install () {
	install -d ${D}${sbindir}
	install -m 755 wpa_supplicant ${D}${sbindir}
	install -m 755 wpa_passphrase ${D}${sbindir}
	install -m 755 wpa_cli        ${D}${sbindir}

	install -d ${D}${localstatedir}/run/wpa_supplicant

	install -d ${D}${docdir}/wpa_supplicant
	install -m 644 README ${D}${docdir}/wpa_supplicant

	install -d ${D}${sysconfdir}/network/if-pre-up.d/
	install -d ${D}${sysconfdir}/network/if-post-down.d/
	install -d ${D}${sysconfdir}/network/if-down.d/

	install -d ${D}${sysconfdir}/wpa_supplicant
	install -m 755 ${WORKDIR}/ifupdown.sh ${D}${sysconfdir}/wpa_supplicant/
	install -m 755 ${WORKDIR}/functions.sh ${D}${sysconfdir}/wpa_supplicant
	
	install -d ${D}/${sysconfdir}/dbus-1/system.d
	install -m 644 ${S}/dbus-wpa_supplicant.conf ${D}/${sysconfdir}/dbus-1/system.d

	ln -s /etc/wpa_supplicant/ifupdown.sh ${D}${sysconfdir}/network/if-pre-up.d/wpasupplicant
	ln -s /etc/wpa_supplicant/ifupdown.sh ${D}${sysconfdir}/network/if-post-down.d/wpasupplicant
}
