SUMMARY = "Cloud Services Manager"
DESCRIPTION = "Interface with user by using voice control"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README.md;md5=32979adb7182a1d4a6b800ee73a173bf"
HOMEPAGE = "https://github.com/olli-ai/cloud-services-manager"
SRCREV = "d1c848512bf79bb005ba7bef44743ecdc37dc5c2"
SRC_URI = " \
			git://git@github.com/olli-ai/cloud-services-manager.git;protocol=ssh;branch=master \
			file://cloud_manager.service \
			file://lost_connection.wav \
		  "
DEPENDS = "dbus nodejs alsa-lib "
RDEPENDS_${PN} += " bash libasound python "
RDEPENDS_${PN}-staticdev += " make perl python "
S = "${WORKDIR}/git"

inherit systemd npm-base

do_install_append () {
	install -d ${D}/home/root/cloud-services-manager
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}${datadir}/sounds/olli

	install -m 0644 ${WORKDIR}/lost_connection.wav ${D}${datadir}/sounds/olli
	install -m 0775 ${WORKDIR}/git/backend_NLP.proto ${D}/home/root/cloud-services-manager/backend_NLP.proto
	install -m 0775 ${WORKDIR}/git/app.js ${D}/home/root/cloud-services-manager/app.js
	install -m 0775 ${WORKDIR}/git/client.js ${D}/home/root/cloud-services-manager/client.js
	install -m 0775 ${WORKDIR}/git/config.json ${D}/home/root/cloud-services-manager/config.json
	install -m 0644 ${WORKDIR}/cloud_manager.service ${D}${systemd_unitdir}/system/cloud_manager.service
	install -m 0775 ${WORKDIR}/git/event.js ${D}/home/root/cloud-services-manager/event.js
	install -m 0775 ${WORKDIR}/git/id.js ${D}/home/root/cloud-services-manager/id.js
	install -m 0775 ${WORKDIR}/git/led.js ${D}/home/root/cloud-services-manager/led.js
	install -m 0775 ${WORKDIR}/git/olli-iviet-558c388af1fb.json ${D}/home/root/cloud-services-manager/olli-iviet-558c388af1fb.json
	install -m 0775 ${WORKDIR}/git/package-lock.json ${D}/home/root/cloud-services-manager/package-lock.json
	install -m 0775 ${WORKDIR}/git/package.json ${D}/home/root/cloud-services-manager/package.json
	install -m 0775 ${WORKDIR}/git/dbusService.js ${D}/home/root/cloud-services-manager/dbusService.js
	install -m 0775 ${WORKDIR}/git/musicPlayer.js ${D}/home/root/cloud-services-manager/musicPlayer.js
	install -m 0775 ${WORKDIR}/git/reqVoice.js ${D}/home/root/cloud-services-manager/reqVoice.js
	install -m 0775 ${WORKDIR}/git/responses.js ${D}/home/root/cloud-services-manager/responses.js	
	install -m 0775 ${WORKDIR}/git/resVoice.js ${D}/home/root/cloud-services-manager/resVoice.js
	install -m 0775 ${WORKDIR}/git/wakeup.js ${D}/home/root/cloud-services-manager/wakeup.js

	oe_runnpm --prefix ${WORKDIR}/git/ install

	cp -R ${WORKDIR}/git/node_modules ${D}/home/root/cloud-services-manager
	rm -R ${D}/home/root/cloud-services-manager/node_modules/put/test
}

SYSTEMD_SERVICE_${PN} = "cloud_manager.service "

FILES_${PN} += " \
				/home/root/cloud-services-manager/* \
				${systemd_unitdir}/system/cloud_manager.service \ 
				"
FILES_${PN}-staticdev += " /home/root/cloud-services-manager/node_modules/* " 