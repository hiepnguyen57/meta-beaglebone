SUMMARY = "restore deivce"
DESCRIPTION = "Auto restore device"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README.md;md5=f1e6a2864e47dd0bb130e7810622ad25"
SRC_URI = " \
            file://restore-device.sh \
            file://restore-device.service \
            file://led-rainbow.service \
            file://ws281x.bin \
            file://rainbow \
            file://led_clear \
            "
RDEPENDS_${PN}-kernel-install += "bash"

inherit systemd

do_install_append () {
    install -d ${D}/opt/backup/scripts
    install -d ${D}${systemd_unitdir}/system
    install -d ${D}${bindir}
    install -d ${D}${libdir}/firmware

    install -m 0644 ${WORKDIR}/restore-device.service ${D}${systemd_unitdir}/system/restore-device.service
    install -m 0644 ${WORKDIR}/led-rainbow.service ${D}${systemd_unitdir}/system/led-rainbow.service
    install -m 0755 ${WORKDIR}/restore-device.sh ${D}/opt/backup/scripts
    install -m 0755 ${WORKDIR}/led_clear     ${D}${bindir}
    install -m 0755 ${WORKDIR}/rainbow ${D}${bindir}
    install -m 0755 ${WORKDIR}/ws281x.bin ${D}${libdir}/firmware
}

FILES_${PN} += " \
                ${systemd_unitdir}/system/restore-device.service \
                ${systemd_unitdir}/system/led-rainbow.service \
                ${libdir}/firmware/ws281x.bin \
                /opt/backup/scripts/restore-device.sh \
                "
SYSTEMD_SERVICE_${PN} = " restore-device.service led-rainbow.service"