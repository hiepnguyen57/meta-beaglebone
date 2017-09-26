SUMMARY = "Music Player Application"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://README.md;md5=7babb7265ab30500931b5bc5b011b67a"
HOMEPAGE = "https://github.com/olli-ai/mp/"
SRCREV = "92cf9a1b9513e5fd7664d3b40eb1e4a8f25ba0c0"
SRC_URI = "git://git@github.com/olli-ai/mp.git;protocol=ssh"
DEPENDS = " glib-2.0 dbus python"

S= "${WORKDIR}/git"

inherit pkgconfig systemd

do_install_append() {
    install -d ${D}/home/root/apps/mp
    install -d ${D}${systemd_unitdir}/system
    install -d ${D}${sysconfdir}/dbus-1/system.d
    install -m 0644 ${WORKDIR}/git/org.olli.music.conf  ${D}${sysconfdir}/dbus-1/system.d/org.olli.music.conf
    install -m 0755 ${WORKDIR}/git/music-player.service ${D}${systemd_unitdir}/system/music-player.service
    install -m 0755 ${WORKDIR}/git/agent.py ${D}/home/root/apps/mp/agent.py
    install -m 0755 ${WORKDIR}/git/bluePlayer.py ${D}/home/root/apps/mp/bluePlayer.py
    install -m 0755 ${WORKDIR}/git/bluetooth.py ${D}/home/root/apps/mp/bluetooth.py
    install -m 0755 ${WORKDIR}/git/bluetooth_irq.py ${D}/home/root/apps/mp/bluetooth_irq.py
    install -m 0755 ${WORKDIR}/git/bluez_device.py ${D}/home/root/apps/mp/bluez_device.py
    install -m 0755 ${WORKDIR}/git/bluezutils.py ${D}/home/root/apps/mp/bluezutils.py
    install -m 0755 ${WORKDIR}/git/contextManager.py ${D}/home/root/apps/mp/contextManager.py
    install -m 0755 ${WORKDIR}/git/events.py ${D}/home/root/apps/mp/events.py
    install -m 0755 ${WORKDIR}/git/log.py ${D}/home/root/apps/mp/log.py
    install -m 0755 ${WORKDIR}/git/mp_main.py ${D}/home/root/apps/mp/mp_main.py
    install -m 0755 ${WORKDIR}/git/mpg123.py ${D}/home/root/apps/mp/mpg123.py
    install -m 0755 ${WORKDIR}/git/pactl.py ${D}/home/root/apps/mp/pactl.py
    install -m 0755 ${WORKDIR}/git/test_fade_volume.py ${D}/home/root/apps/mp/test_fade_volume.py
    install -m 0755 ${WORKDIR}/git/test_method.py ${D}/home/root/apps/mp/test_method.py
    install -m 0755 ${WORKDIR}/git/test_pulse.py ${D}/home/root/apps/mp/test_pulse.py
    install -m 0755 ${WORKDIR}/git/test_signal.py ${D}/home/root/apps/mp/test_signal.py
    install -m 0755 ${WORKDIR}/git/test_wakeword.py ${D}/home/root/apps/mp/test_wakeword.py
    install -m 0755 ${WORKDIR}/git/webPlayer.py ${D}/home/root/apps/mp/webPlayer.py
}

SYSTEMD_SERVICE_${PN} = "music-player.service "

FILES_${PN} += "${sysconfdir} ${systemd_unitdir} /home/root/apps/mp"
FILES_${PN}-conf = "${sysconfdir}"
RDEPENDS_${PN} += "python"