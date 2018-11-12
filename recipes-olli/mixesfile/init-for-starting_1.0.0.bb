SUMMARY= "Init for starting"
DESCRIPTION = " Added some files which need to start music player"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README.md;md5=d41d8cd98f00b204e9800998ecf8427e"

SRC_URI = " \
			file://kernel-load-micarray.service \
			file://asound.conf \
			file://machine-info \
			file://DFU_FLASHING_INTO_INTERNAL_FLASH.sh \
			file://Init_For_Starting.sh \
			file://reset.sh \
			file://tlv320aic.sh \
			file://usbaudio.sh \
		"

DEPENDS = "alsa-lib "
RDEPENDS_${PN} += "bash "
S = "${WORKDIR/git}"

inherit systemd pkgconfig

do_install_append() {
	install -d ${D}/home/root/mixes
	install -d ${D}${sysconfdir}
	install -d ${D}${systemd_unitdir}/system

	install -m 0775 ${WORKDIR}/DFU_FLASHING_INTO_INTERNAL_FLASH.sh ${D}/home/root/mixes/DFU_FLASHING_INTO_INTERNAL_FLASH.sh
	install -m 0775 ${WORKDIR}/Init_For_Starting.sh ${D}/home/root/mixes/Init_For_Starting.sh
	install -m 0644 ${WORKDIR}/asound.conf ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/kernel-load-micarray.service ${D}${systemd_unitdir}/system/kernel-load-micarray.service
	install -m 0644 ${WORKDIR}/machine-info ${D}${sysconfdir}
	install -m 0775 ${WORKDIR}/reset.sh ${D}/home/root/mixes/reset.sh
	install -m 0775 ${WORKDIR}/tlv320aic.sh ${D}/home/root/mixes/tlv320aic.sh
	install -m 0775 ${WORKDIR}/usbaudio.sh ${D}/home/root/mixes/usbaudio.sh
}

SYSTEMD_SERVICE_${PN} = "kernel-load-micarray.service"

FILES_${PN} += " \
	/home/root/mixes/* \
	${systemd_unitdir}/system/kernel-load-micarray.service \
"
