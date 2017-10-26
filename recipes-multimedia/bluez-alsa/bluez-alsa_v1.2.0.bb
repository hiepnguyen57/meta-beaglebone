SUMMARY = "Bluetooth Audio ALSA Backend"
HOMEPAGE = "https://github.com/Arkq/bluez-alsa"
SECTION = "libs/multimedia"
LICENSE = " MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=bb3e99e80c5d718213f35ae1def4c106"
SRC_URI = " \
			git://git@github.com/Arkq/bluez-alsa.git;protocol=https \
			file://bluez-alsa.service \
			"
SRCREV = "9769323899cfbaf21c1d8710eaa997841b127095"
SRC_URI[md5sum] = "4515aafb55bc7dddfb7462d2d615a7f2"
SRC_URI[sha256sum] = "38df9f50b37617d48d09f8374952a38ec770939dab1202c321749103950f8192"

inherit autotools pkgconfig bluetooth systemd
DEPENDS += "alsa-lib glib-2.0 libfdk-aac bluez5 sbc ortp "
S= "${WORKDIR}/git"
EXTRA_OECONF = " \
	--enable-aac \
	"

USERADD_PACKAGES = "bluez-alsa "
GROUPADD_PARAM_bluez-alsa = "-r bluetooth; -r audio"
USERADD_PARAM_bluez-alsa = "--system -G audio,bluetooth --gid audio root"

do_install_append () {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/bluez-alsa.service ${D}${systemd_unitdir}/system 
}

FILES_${PN} += " ${datadir}/* \
				${systemd_unitdir}/system/bluez-alsa.service "
FILES_${PN}-staticdev += " ${libdir}/alsa-lib/*.so ${libdir}/alsa-lib/*.la "
SYSTEMD_SERVICE_${PN} = " bluez-alsa.service"