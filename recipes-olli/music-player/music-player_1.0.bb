SUMMARY = "Music Player Application"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://README.md;md5=7babb7265ab30500931b5bc5b011b67a"
HOMEPAGE = "https://github.com/olli-ai/mp/"
SRCREV = "1d75d5a5d89b62c5953db5275be44f176792363b"
SRC_URI = " \
            git://git@github.com/olli-ai/mp.git;protocol=ssh \
            file://music-player.service \
            file://org.olli.music.conf \
            "
DEPENDS = " glib-2.0 dbus python"

S= "${WORKDIR}/git"

inherit pkgconfig systemd

do_install_append() {
    install -d ${D}/home/root/music-player
    install -d ${D}${systemd_unitdir}/system
    install -d ${D}${sysconfdir}/dbus-1/system.d

    install -m 0644 ${WORKDIR}/org.olli.music.conf  ${D}${sysconfdir}/dbus-1/system.d/org.olli.music.conf
    install -m 0644 ${WORKDIR}/music-player.service ${D}${systemd_unitdir}/system/music-player.service
    install -m 0755 ${WORKDIR}/git/agent.py ${D}/home/root/music-player/agent.py
    install -m 0755 ${WORKDIR}/git/bluePlayer.py ${D}/home/root/music-player/bluePlayer.py
    install -m 0755 ${WORKDIR}/git/bluetooth.py ${D}/home/root/music-player/bluetooth.py
    install -m 0755 ${WORKDIR}/git/bluetooth_irq.py ${D}/home/root/music-player/bluetooth_irq.py
    install -m 0755 ${WORKDIR}/git/bluez_device.py ${D}/home/root/music-player/bluez_device.py
    install -m 0755 ${WORKDIR}/git/bluezutils.py ${D}/home/root/music-player/bluezutils.py
    install -m 0755 ${WORKDIR}/git/contextManager.py ${D}/home/root/music-player/contextManager.py
    install -m 0755 ${WORKDIR}/git/events.py ${D}/home/root/music-player/events.py
    install -m 0755 ${WORKDIR}/git/log.py ${D}/home/root/music-player/log.py
    install -m 0755 ${WORKDIR}/git/mplayer_main.py ${D}/home/root/music-player/mplayer_main.py
    install -m 0755 ${WORKDIR}/git/mpg123.py ${D}/home/root/music-player/mpg123.py
    install -m 0755 ${WORKDIR}/git/test_fade_volume.py ${D}/home/root/music-player/test_fade_volume.py
    install -m 0755 ${WORKDIR}/git/test_method.py ${D}/home/root/music-player/test_method.py
    install -m 0755 ${WORKDIR}/git/test_pulse.py ${D}/home/root/music-player/test_pulse.py
    install -m 0755 ${WORKDIR}/git/test_signal.py ${D}/home/root/music-player/test_signal.py
    install -m 0755 ${WORKDIR}/git/test_wakeword.py ${D}/home/root/music-player/test_wakeword.py
    install -m 0755 ${WORKDIR}/git/webPlayer.py ${D}/home/root/music-player/webPlayer.py
    install -m 0755 ${WORKDIR}/git/amixer.py ${D}/home/root/music-player/amixer.py
}

SYSTEMD_SERVICE_${PN} = "music-player.service "

FILES_${PN} += " \
                ${systemd_unitdir}/system/music-player.service \
                ${sysconfdir}/dbus-1/system.d/org.olli.music.conf \
                /home/root/music-player/* \
                "
FILES_${PN}-conf = "${sysconfdir}"
RDEPENDS_${PN} += "python"