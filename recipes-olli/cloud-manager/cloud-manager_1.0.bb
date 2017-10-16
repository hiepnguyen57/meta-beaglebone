SUMMARY = "this is a summary"
DESCRIPTION = "this is a description "
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README.md;md5=32979adb7182a1d4a6b800ee73a173bf"
HOMEPAGE = "git@github.com:olli-ai/cloud-services-manager.git"
SRCREV = "02b3d1e8a11ce3d7d7c47cbc680ba19177abd96f"
SRC_URI = "	\
			git://git@github.com/olli-ai/cloud-services-manager.git;protocol=ssh \
			"
DEPENDS = " glib-2.0 dbus portaudio-v19 portaudio-v19 atlas-base "
S= "${WORKDIR}/git"

inherit npm-base systemd

do_install_append() {
	install -d ${D}${sysconfdir}/cloud-manager
	oe_runnpm --prefix ${S} install

	install -m 644 ${S}/backend_NLP.proto ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/client.js ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/client_promise.js ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/cloud_manager.service ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/config.json ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/event.js ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/id.js ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/mplayer.js ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/node_modules ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/olli-iviet-558c388af1fb.json ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/record.js ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/testmplayer.js ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/testrecord.js ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/package-lock.json ${D}${sysconfdir}/cloud-manager
	install -m 644 ${S}/package.json ${D}${sysconfdir}/cloud-manager
}