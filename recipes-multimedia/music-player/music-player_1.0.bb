SUMMARY = "this is a summary"
DESCRIPTION = "this is a description "
LICENSE = "Olli"
LIC_FILES_CHKSUM = "file://README.md;md5=5f3c3c642840155e947abe32e9d35c44"
HOMEPAGE = "https://github.com/olli-ai/music-player"
SRCREV = "482a887860046bc391a566ffa9dfe734dacb59e8"
SRC_URI = "git://git@github.com/olli-ai/music-player.git;protocol=ssh"
DEPENDS += " glib-2.0 dbus libsoc glibc"

S= "${WORKDIR}/git"

inherit pkgconfig systemd

do_compile () {
	oe_runmake -C ${S}
}

do_install_append() {
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}/olli_bluetooth
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}${sysconfdir}/dbus-1/system.d

	install -m 0775 ${WORKDIR}/git/scripts/volumeup.sh ${D}${sysconfdir}/olli_bluetooth/volumeup.sh
	install -m 0775 ${WORKDIR}/git/output/music-player ${D}${bindir}/music-player
	install -m 0644 ${WORKDIR}/git/scripts/musicPlayer.service ${D}${systemd_unitdir}/system/musicPlayer.service
	install -m 0644 ${WORKDIR}/git/scripts/org.music.conf ${D}${sysconfdir}/dbus-1/system.d/org.music.conf
}

SYSTEMD_SERVICE_${PN} = "musicPlayer.service "
FILES_${PN} = "${systemd_unitdir}/system/* ${bindir}/music-player ${sysconfdir}/dbus-1/system.d/org.music.conf ${sysconfdir}/olli_bluetooth/volumeup.sh "
FILES_${PN}-conf = "${sysconfdir}"