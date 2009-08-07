DESCRIPTION = "GTK+ applet for NetworkManager" 
LICENSE = "GPL"
DEPENDS = "networkmanager dbus-glib libglade gconf gnome-keyring libnotify"
#TODO DEPENDS libnotify
RDEPENDS = "networkmanager dbus-wait"

inherit gnome gtk-icon-cache
SRC_URI="ftp://ftp.gnome.org/pub/GNOME/sources/network-manager-applet/0.7/network-manager-applet-${PV}.tar.gz \
           file://no-werror.patch;patch=0 \
           file://adhocMode-multiple-wep-index-support.patch;patch=0 \
           file://adhocMode-shared-authentication-support.patch;patch=0 \
           file://70NetworkManagerApplet.shbg \
"

EXTRA_OECONF = " \
		--with-distro=debian \
		--with-crypto=gnutls \
		--disable-more-warnings"

inherit autotools pkgconfig update-rc.d

INITSCRIPT_NAME = "networkmanager-applet"
INITSCRIPT_PARAMS = "defaults 28"
		
S = "${WORKDIR}/network-manager-applet-${PV}"

export UUID_CFLAGS=-I${STAGING_INCDIR}/uuid
export POLKIT_CFLAGS="-DNO_POLKIT_GNOME -I${STAGING_INCDIR}/PolicyKit"

FILES_${PN} += "${datadir}/nm-applet/ \
        ${datadir}/gnome-vpn-properties/ \
        ${datadir}/gnome/autostart/ \
        "

do_install_append () {
    install -d ${D}${sysconfdir}/X11/Xsession.d/
    install -m 755 ${WORKDIR}/70NetworkManagerApplet.shbg ${D}${sysconfdir}/X11/Xsession.d/
}

