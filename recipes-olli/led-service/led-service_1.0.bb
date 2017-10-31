SUMMARY = "Led service"
DESCRIPTION = "Led service"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README.md;md5=d1a392063916cfb2ce577d226e43b735"
HOMEPAGE = "https://github.com/olli-ai/led-service.git"
SRCREV = "a1d4245c6d6233368d91b09a3135e81ab65458db"
SRC_URI = " \
			git://git@github.com/olli-ai/led-service.git;protocol=ssh;branch=master \
		  "

S = "${WORKDIR}/git"

inherit systemd npm-base

do_install_append () {
	install -d ${D}/home/root/led-service
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}${sysconfdir}/dbus-1/system.d
	install -d ${D}${bindir}

	install -m 0755 ${WORKDIR}/git/effects.py ${D}/home/root/led-service
	install -m 0644 ${WORKDIR}/git/led.service	 ${D}${systemd_unitdir}/system
	install -m 0755 ${WORKDIR}/git/led_clear	 ${D}${bindir}
	install -m 0755 ${WORKDIR}/git/led_fade	 ${D}${bindir}
	install -m 0755 ${WORKDIR}/git/led_rainbow	 ${D}${bindir}
	install -m 0755 ${WORKDIR}/git/led_volume	 ${D}${bindir}
	install -m 0755 ${WORKDIR}/git/led_wakeup	 ${D}${bindir}
	install -m 0755 ${WORKDIR}/git/led_wakeup_on ${D}${bindir}
	install -m 0755 ${WORKDIR}/git/led_wakeup_off ${D}${bindir}
	install -m 0755 ${WORKDIR}/git/led_spin ${D}${bindir}
	install -m 0755 ${WORKDIR}/git/manager.py	 ${D}/home/root/led-service
	install -m 0644 ${WORKDIR}/git/org.olli.led.conf	 ${D}${sysconfdir}/dbus-1/system.d
	install -m 0755 ${WORKDIR}/git/startupLed.py	 ${D}/home/root/led-service
	install -m 0644 ${WORKDIR}/git/startupLed.service		 ${D}${systemd_unitdir}/system
}

SYSTEMD_SERVICE_${PN} = "startupLed.service  led.service"
FILES_${PN} += " /home/root/led-service/* ${systemd_unitdir}/system/* ${sysconfdir}/dbus-1/system.d/* "

