SUMMARY = "this is a summary"
DESCRIPTION = "this is a description "
LICENSE = "Olli"
LIC_FILES_CHKSUM = "file://README.md;md5=db578ad8efc88729badff79cf47aff39"
HOMEPAGE = "https://github.com/olli-ai/wifi-manager"
SRCREV = "b145ea386ff9d6bb0861c7ad5329dd38d27d15a5"
SRC_URI = "git://git@github.com/olli-ai/wifi-manager.git;protocol=ssh \
			file://beeeep.mp3 \
"
DEPENDS += " nodejs glibc networkmanager"
RDEPENDS_${PN} += " bash  "

S= "${WORKDIR}/git"

inherit npm-base

do_install_append() {
	install -d ${D}${sysconfdir}/wifi_manager
	install -d ${D}${sysconfdir}/wifi_manager
	install -d ${D}${sysconfdir}/sound
	install -d ${D}${sysconfdir}/olli_wifi
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}${sysconfdir}/NetworkManager/
	install -d ${D}${sysconfdir}/NetworkManager/system-connections

	oe_runnpm --prefix ${WORKDIR}/git/ install

	install -m 0775 ${WORKDIR}/git/conf/wifi-ap1 ${D}${sysconfdir}/NetworkManager/system-connections/dg-ap
	install -m 0775 ${WORKDIR}/git/scripts/open-wifi.sh ${D}${sysconfdir}/olli_wifi/open-wifi.sh
	install -m 0775 ${WORKDIR}/git/olli_wifi.service ${D}${systemd_unitdir}/system/olli_wifi.service
	install -m 0775 ${WORKDIR}/git/index.html ${D}${sysconfdir}/wifi_manager/index.html
	install -m 0775 ${WORKDIR}/git/wifi_list.json ${D}${sysconfdir}/wifi_manager/wifi_list.json
	install -m 0775 ${WORKDIR}/git/index.js ${D}${sysconfdir}/wifi_manager/index.js
	install -m 0775 ${WORKDIR}/git/package.json ${D}${sysconfdir}/wifi_manager/package.json
	install -m 0775 ${WORKDIR}/beeeep.mp3 ${D}${sysconfdir}/sound/beeeep.mp3

	cp -R ${WORKDIR}/git/node_modules ${D}${sysconfdir}/wifi_manager/
	rm -R ${D}${sysconfdir}/wifi_manager/node_modules/put/test
}

SYSTEMD_SERVICE_${PN} = "olli_wifi.service "

FILES_${PN}-conf = "${sysconfdir}"
FILES_${PN} += " ${systemd_unitdir} ${sysconfdir}/sound/* ${sysconfdir}/wifi_manager/* ${sysconfdir}/wifi_manager/* ${sysconfdir}/olli_wifi/open-wifi.sh ${sysconfdir}/wifi_manager/wifi_list.json "
FILES_${PN} += "${sysconfdir}/wifi_manager/node_modules/"
