SUMMARY = "Cloud Services Manager"
DESCRIPTION = "Interface with user by using voice control"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README.md;md5=32979adb7182a1d4a6b800ee73a173bf"
HOMEPAGE = "https://github.com/olli-ai/cloud-services-manager"
SRCREV = "02b3d1e8a11ce3d7d7c47cbc680ba19177abd96f"
SRC_URI = " \
			git://git@github.com/olli-ai/cloud-services-manager.git;protocol=ssh;branch=master \
			file://cloud_manager.service \
			file://node_modules \
		  "
DEPENDS = "dbus nodejs"
RDEPENDS_${PN} += " bash  "

S = "${WORKDIR}/git"

inherit systemd

do_install_append () {
	install -d ${D}/home/root/cloud-services-manager
	install -d ${D}${systemd_unitdir}/system

	install -m 0775 ${WORKDIR}/git/backend_NLP.proto ${D}/home/root/cloud-services-manager/backend_NLP.proto
	install -m 0775 ${WORKDIR}/git/client.js ${D}/home/root/cloud-services-manager/client.js
	install -m 0775 ${WORKDIR}/git/client_promise.js ${D}/home/root/cloud-services-manager/client_promise.js
	install -m 0775 ${WORKDIR}/git/config.json ${D}/home/root/cloud-services-manager/config.json
	install -m 0644 ${WORKDIR}/cloud_manager.service ${D}${systemd_unitdir}/system/cloud_manager.service
	install -m 0775 ${WORKDIR}/git/event.js ${D}/home/root/cloud-services-manager/event.js
	install -m 0775 ${WORKDIR}/git/id.js ${D}/home/root/cloud-services-manager/id.js
	install -m 0775 ${WORKDIR}/git/mplayer.js ${D}/home/root/cloud-services-manager/mplayer.js
	install -m 0775 ${WORKDIR}/git/olli-iviet-558c388af1fb.json ${D}/home/root/cloud-services-manager/olli-iviet-558c388af1fb.json
	install -m 0775 ${WORKDIR}/git/package-lock.json ${D}/home/root/cloud-services-manager/package-lock.json
	install -m 0775 ${WORKDIR}/git/package.json ${D}/home/root/cloud-services-manager/package.json
	install -m 0775 ${WORKDIR}/git/record.js ${D}/home/root/cloud-services-manager/record.js
	install -m 0775 ${WORKDIR}/git/testmplayer.js ${D}/home/root/cloud-services-manager/testmplayer.js
	install -m 0775 ${WORKDIR}/git/testrecord.js ${D}/home/root/cloud-services-manager/testrecord.js

	cp -R ${WORKDIR}/node_modules ${D}/home/root/cloud-services-manager/

}

SYSTEMD_SERVICE_${PN} = "cloud_manager.service "

FILES_${PN} += " \
				/home/root/cloud-services-manager/* \
				${systemd_unitdir}/system/cloud_manager.service \
				/home/root/cloud-services-manager/node_modules/ \ 
				"