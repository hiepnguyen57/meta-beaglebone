SUMMARY = "reset-button"
DESCRIPTION = "Button is pressed to reset OS "
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README;md5=55a6daef2f668716188cb8577b8fdcc4"
SRC_URI = " \
            file://reset-button \
            file://reset-button.service \
            "

DEPENDS = "glib-2.0 libsoc"

inherit pkgconfig systemd

do_install_append () {
    install -d ${D}${bindir}
    install -d ${D}${systemd_unitdir}/system

    install -m 0644 ${WORKDIR}/reset-button.service ${D}${systemd_unitdir}/system/reset-button.service 
    install -m 0755 ${WORKDIR}/reset-button ${D}${bindir}/


}

FILES_${PN} += " \
                ${bindir}/* \
                ${systemd_unitdir}/system/reset-button.service \
                "
SYSTEMD_SERVICE_${PN} = " reset-button.service"