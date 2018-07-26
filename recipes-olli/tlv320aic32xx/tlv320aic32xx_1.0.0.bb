SUMMARY = "tlv320aic32xx"
DESCRIPTION = "Configuration Audio Codec"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README;md5=e92f40d3794b7e5384249985fd10d192"
SRC_URI = " \
            file://tlv320aic32xx_ctrl \
            "

DEPENDS = "glib-2.0 libsoc i2c-tools"

inherit pkgconfig systemd

do_install_append () {
    install -d ${D}${bindir}

    install -m 0755 ${WORKDIR}/tlv320aic32xx_ctrl ${D}${bindir}/


}

FILES_${PN} += " \
                ${bindir}/* \
                "