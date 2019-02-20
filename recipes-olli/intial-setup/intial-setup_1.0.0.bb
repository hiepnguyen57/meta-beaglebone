SUMMARY= "Init for starting"
DESCRIPTION = " Added some files which need to start music player"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README.md;md5=a798e7084bab7b87989b3717e64189b2"

SRC_URI = " \
			file://README.md \
			file://kernel-load-micarray.service \
			file://asound.conf \
			file://machine-info \
			file://DFU_FLASHING_INTO_INTERNAL_FLASH.sh \
			file://init_pins.sh \
			file://reset.sh \
			file://tlv320aic.sh \
			file://usbaudio.sh \
		"

DEPENDS = "alsa-lib "
RDEPENDS_${PN} += "bash "
S = "${WORKDIR}"

inherit systemd pkgconfig

do_install_append() {
	install -d ${D}/home/root/container
	install -d ${D}${sysconfdir}
	install -d ${D}${systemd_unitdir}/system

	install -m 0775 ${WORKDIR}/DFU_FLASHING_INTO_INTERNAL_FLASH.sh ${D}/home/root/container/DFU_FLASHING_INTO_INTERNAL_FLASH.sh
	install -m 0775 ${WORKDIR}/init_pins.sh ${D}/home/root/container/init_pins.sh
	install -m 0644 ${WORKDIR}/asound.conf ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/kernel-load-micarray.service ${D}${systemd_unitdir}/system/kernel-load-micarray.service
	install -m 0644 ${WORKDIR}/machine-info ${D}${sysconfdir}
	install -m 0775 ${WORKDIR}/reset.sh ${D}/home/root/container/reset.sh
	install -m 0775 ${WORKDIR}/tlv320aic.sh ${D}/home/root/container/tlv320aic.sh
	install -m 0775 ${WORKDIR}/usbaudio.sh ${D}/home/root/container/usbaudio.sh
}

SYSTEMD_SERVICE_${PN} = "kernel-load-micarray.service"

FILES_${PN} += " \
	/home/root/container/* \
	${systemd_unitdir}/system/kernel-load-micarray.service \
"
