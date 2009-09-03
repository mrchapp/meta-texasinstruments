DESCRIPTION = "GStreamer Test Harness - Insanity"
SECTION = "devel/python"
RDEPENDS = "python-gst python-pygtk python-threading python-math python-compression python-logging python-dbus python-subprocess python-resource python-pysqlite2"
PRIORITY = "optional"
LICENSE = "GPL"
PR = "r0"

SRC_URI = "git://git.collabora.co.uk/git/user/edward/gst-qa-system;protocol=git"
SRCREV = "41a399f23c2f0f6c9ee78fc1951b5081ecf860d1"
S = "${WORKDIR}/git"

inherit distutils

FILES_${PN} += "/usr/share/"

