SUMMARY = "this is a summary"
DESCRIPTION = "this is a description "
LICENSE = "Olli"
LIC_FILES_CHKSUM = "file://README.md;md5=756173123cb8cc19c27506ca942ebe82"
HOMEPAGE = "https://github.com/olli-ai/olli-daemon/"
SRCREV = "5016b1fb797ed39782ae8dd6d7f9485b40fa6c7c"
SRC_URI = "git://git@github.com/olli-ai/olli-daemon.git;protocol=ssh"
DEPENDS += " glib-2.0 dbus libsoc"

S= "${WORKDIR}/git"

inherit pkgconfig systemd

do_compile () {
	oe_runmake -C ${S}
}

do_install_append() {
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}/buttons_service
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}${sysconfdir}/dbus-1/system.d
	install -m 0775 ${WORKDIR}/git/output/olli-daemon ${D}${bindir}/olli-daemon
	install -m 0644 ${WORKDIR}/git/buttons.service ${D}${systemd_unitdir}/system/buttons.service
	install -m 0644 ${WORKDIR}/git/buttons.conf ${D}${sysconfdir}/buttons_service/buttons.conf
	install -m 0644 ${WORKDIR}/git/olli-daemon.conf ${D}${sysconfdir}/dbus-1/system.d/olli-daemon.conf
	install -m 0644 ${WORKDIR}/git/libsoc.conf ${D}${sysconfdir}/libsoc.conf
	install -m 0775 ${WORKDIR}/git/test/test_signal.py ${D}${sysconfdir}/buttons_service/test_signal.py
}

SYSTEMD_SERVICE_${PN} = "buttons.service "

FILES_${PN}-conf = "${sysconfdir}"