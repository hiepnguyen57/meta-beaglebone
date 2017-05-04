SUMMARY = "this is a summary"
DESCRIPTION = "this is a description "
LICENSE = "Olli"
LIC_FILES_CHKSUM = "file://README.md;md5=86af6ca843bbd830e486384d041898c0"
HOMEPAGE = "https://github.com/olli-ai/display-server"
SRCREV = "29d23dc5f91188d53e0d47c6be1962fe98f634d4"
SRC_URI = "git://git@github.com/olli-ai/display-server.git;protocol=ssh;branch=dev"
DEPENDS += " glib-2.0 dbus libsoc glibc"

S= "${WORKDIR}/git"

inherit pkgconfig systemd

do_compile () {
	oe_runmake -C ${S}
}

do_install_append() {
	install -d ${D}${bindir}
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}${sysconfdir}/dbus-1/system.d

	install -m 0775 ${WORKDIR}/git/output/display-server ${D}${bindir}/display-server
	install -m 0644 ${WORKDIR}/git/display-server.service ${D}${systemd_unitdir}/system/display-server.service
	install -m 0644 ${WORKDIR}/git/org.lcd.conf ${D}${sysconfdir}/dbus-1/system.d/org.lcd.conf
}

SYSTEMD_SERVICE_${PN} = "display-server.service "
FILES_${PN} = "${systemd_unitdir}/system/* ${bindir}/display-server ${sysconfdir}/dbus-1/system.d/org.lcd.conf "
FILES_${PN}-conf = "${sysconfdir}"