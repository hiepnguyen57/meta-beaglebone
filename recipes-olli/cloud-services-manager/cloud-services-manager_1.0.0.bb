SUMMARY = "Cloud Services Manager"
DESCRIPTION = "Interface with user by using voice control"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README.md;md5=32979adb7182a1d4a6b800ee73a173bf"
HOMEPAGE = "https://github.com/olli-ai/cloud-services-manager"
SRCREV = "94be9e80a76de3d1e7bc954fb3910af14242e7d9"
SRC_URI = " \
			git://git@github.com/olli-ai/cloud-services-manager.git;protocol=ssh;branch=master \
			file://cloud_manager.service \
		  "
DEPENDS = "dbus nodejs alsa-lib "
RDEPENDS_${PN} += " bash libasound python "
RDEPENDS_${PN}-staticdev += " make perl python "
S = "${WORKDIR}/git"

inherit systemd npm-base

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