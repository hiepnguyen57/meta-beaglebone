SUMMARY = "this is a summary"
DESCRIPTION = "this is a description "
LICENSE = "Olli"
LIC_FILES_CHKSUM = "file://README.md;md5=db578ad8efc88729badff79cf47aff39"
HOMEPAGE = "https://github.com/olli-ai/wifi-manager"
SRCREV = "b07176542c80778a52eb339790cc0546307d849f"
SRC_URI = "git://git@github.com/olli-ai/wifi-manager.git;protocol=ssh \
			file://beeeep.mp3 \
"
DEPENDS += " nodejs glibc networkmanager"
RDEPENDS_${PN} += " bash  "

S= "${WORKDIR}/git"

inherit npm-base systemd

do_install_append() {
	install -d ${D}/home/root/wifi-manager
	install -d ${D}${systemd_unitdir}/system

	oe_runnpm --prefix ${WORKDIR}/git/ install

	install -m 0644 ${WORKDIR}/git/olli_wifi.service ${D}${systemd_unitdir}/system/olli_wifi.service
	install -m 0644 ${WORKDIR}/git/wifi_ap.service ${D}${systemd_unitdir}/system/wifi_ap.service
	install -m 0775 ${WORKDIR}/git/index.html ${D}/home/root/wifi-manager/index.html
	install -m 0775 ${WORKDIR}/git/wifi_list.json ${D}/home/root/wifi-manager/wifi_list.json
	install -m 0775 ${WORKDIR}/git/index.js ${D}/home/root/wifi-manager/index.js
	install -m 0775 ${WORKDIR}/git/package.json ${D}/home/root/wifi-manager/package.json

	cp -R ${WORKDIR}/git/node_modules ${D}/home/root/wifi-manager/
	rm -R ${D}/home/root/wifi-manager/node_modules/put/test
}

SYSTEMD_SERVICE_${PN} = "olli_wifi.service wifi_ap.service "

FILES_${PN} += " ${systemd_unitdir}  /home/root/wifi-manager/* /home/root/wifi-manager/* /home/root/olli_wifi/open-wifi.sh /home/root/wifi-manager/wifi_list.json "
FILES_${PN} += "/home/root/wifi-manager/node_modules/"
