SUMMARY= "Music Player"
DESCRIPTION = "Interface with user by using voice control"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README.md;md5=4f6e52a216db167c77c701face423393"
HOMEPAGE = "https://github.com/olli-ai/new-music-player.git"
SRCREV = "a9ca7422f46a2b945d30c89871a442d0a5546188"
SRC_URI = " \
			git://git@github.com/olli-ai/new-music-player.git;protocol=ssh;branch=master \
			file://music-player.service \
			file://playurl \
			file://mpg123_https.sh \
			file://sip.cfg \
		"
DEPENDS = "dbus nodejs alsa-lib libao"
RDEPENDS_${PN} += " bash libasound python libcurl "
RDEPENDS_${PN}-staticdev += " make perl python "
S = "${WORKDIR}/git"

inherit systemd npm-base

do_install_append () {
	install -d ${D}/home/root/music-player
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}/home/root/music-player/bluez

	install -m 0775 ${WORKDIR}/git/bluez/Adapter.js ${D}/home/root/music-player/bluez/Adapter.js
	install -m 0775 ${WORKDIR}/git/bluez/Agent.js ${D}/home/root/music-player/bluez/Agent.js
	install -m 0775 ${WORKDIR}/git/bluez/Bluez.js ${D}/home/root/music-player/bluez/Bluez.js
	install -m 0775 ${WORKDIR}/git/bluez/Device.js ${D}/home/root/music-player/bluez/Device.js
	install -m 0775 ${WORKDIR}/git/bluez/Profile.js ${D}/home/root/music-player/bluez/Profile.js

	install -m 0775 ${WORKDIR}/git/agent.py ${D}/home/root/music-player/agent.py
	install -m 0775 ${WORKDIR}/git/amixer.js ${D}/home/root/music-player/amixer.js
	install -m 0775 ${WORKDIR}/git/app.js ${D}/home/root/music-player/app.js
	install -m 0775 ${WORKDIR}/git/bluePlayer.js ${D}/home/root/music-player/bluePlayer.js
	install -m 0775 ${WORKDIR}/git/bluetooth.js ${D}/home/root/music-player/bluetooth.js
	install -m 0775 ${WORKDIR}/git/config.json ${D}/home/root/music-player/config.json
	install -m 0775 ${WORKDIR}/git/credentials.json ${D}/home/root/music-player/credentials.json
	install -m 0775 ${WORKDIR}/git/event.js ${D}/home/root/music-player/event.js
	install -m 0775 ${WORKDIR}/git/gd_bundle-g2-g1.crt ${D}/home/root/music-player/gd_bundle-g2-g1.crt
	install -m 0775 ${WORKDIR}/git/ioctl.js ${D}/home/root/music-player/ioctl.js
	install -m 0775 ${WORKDIR}/git/mp_events.js ${D}/home/root/music-player/mp_events.js
	install -m 0775 ${WORKDIR}/git/mpg123.js ${D}/home/root/music-player/mpg123.js
	install -m 0775 ${WORKDIR}/mpg123_https.sh ${D}/home/root/music-player/mpg123_https.sh
	install -m 0644 ${WORKDIR}/music-player.service ${D}${systemd_unitdir}/system/music-player.service
	install -m 0775 ${WORKDIR}/git/music_player.js ${D}/home/root/music-player/music_player.js
	install -m 0775 ${WORKDIR}/git/olli-iviet-0ce2dda01741.json ${D}/home/root/music-player/olli-iviet-0ce2dda01741.json
	install -m 0775 ${WORKDIR}/git/package.json ${D}/home/root/music-player/package.json
	install -m 0775 ${WORKDIR}/sip.cfg ${D}/home/root/music-player/sip.cfg
	install -m 0775 ${WORKDIR}/playurl ${D}/home/root/music-player/playurl
	install -m 0775 ${WORKDIR}/git/webPlayer.js ${D}/home/root/music-player/webPlayer.js

	oe_runnpm --prefix ${WORKDIR}/git/ install

	cp -R ${WORKDIR}/git/node_modules ${D}/home/root/music-player
	rm -R ${D}/home/root/music-player/node_modules/put/test

}

SYSTEMD_SERVICE_${PN} = "music-player.service "

FILES_${PN} += " \
				/home/root/music-player/* \
				${systemd_unitdir}/system/music-player.service \
				"

FILES_${PN}-staticdev += " /home/root/music-player/node_modules/* "
