SUMMARY= "Music Player"
DESCRIPTION = "Interface with user by using voice control"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README.md;md5=4f6e52a216db167c77c701face423393"
HOMEPAGE = "https://github.com/olli-ai/olli-device-sdk.git"
SRCREV = "3042c9e95e58bb4c866d47e5597d2cda3b427588"
SRC_URI = " \
			git://git@github.com/olli-ai/olli-device-sdk.git;protocol=ssh;branch=master \
			file://olli-device-sdk.service \
		"
DEPENDS = "dbus nodejs alsa-lib libao"
RDEPENDS_${PN} += " bash libasound python libcurl "
RDEPENDS_${PN}-staticdev += " make perl python "
S = "${WORKDIR}/git"

inherit systemd npm-base

do_install_append () {
	install -d ${D}/home/root/olli-device-sdk
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}/home/root/olli-device-sdk/AdjustVolume
	install -d ${D}/home/root/olli-device-sdk/Apps
	install -d ${D}/home/root/olli-device-sdk/AudioPlayer
	install -d ${D}/home/root/olli-device-sdk/bluez
	install -d ${D}/home/root/olli-device-sdk/conf
	install -d ${D}/home/root/olli-device-sdk/ContextManager
	install -d ${D}/home/root/olli-device-sdk/i2c-control
	install -d ${D}/home/root/olli-device-sdk/MediaPlayer
	install -d ${D}/home/root/olli-device-sdk/Network
	install -d ${D}/home/root/olli-device-sdk/reminder_files
	install -d ${D}/home/root/olli-device-sdk/Sounds
	install -d ${D}/home/root/olli-device-sdk/wakeword

	install -m 0775 ${WORKDIR}/git/bluez/Adapter.js ${D}/home/root/olli-device-sdk/bluez/Adapter.js
	install -m 0775 ${WORKDIR}/git/bluez/Agent.js ${D}/home/root/olli-device-sdk/bluez/Agent.js
	install -m 0775 ${WORKDIR}/git/bluez/Bluez.js ${D}/home/root/olli-device-sdk/bluez/Bluez.js
	install -m 0775 ${WORKDIR}/git/bluez/Device.js ${D}/home/root/olli-device-sdk/bluez/Device.js
	install -m 0775 ${WORKDIR}/git/bluez/Profile.js ${D}/home/root/olli-device-sdk/bluez/Profile.js

	install -m 0775 ${WORKDIR}/git/conf/credentials.json ${D}/home/root/olli-device-sdk/conf/credentials.json
	install -m 0775 ${WORKDIR}/git/conf/gd_bundle-g2-g1.crt ${D}/home/root/olli-device-sdk/conf/gd_bundle-g2-g1.crt
	install -m 0775 ${WORKDIR}/git/conf/config.json ${D}/home/root/olli-device-sdk/conf/config.json
	install -m 0775 ${WORKDIR}/git/conf/olli-iviet-0ce2dda01741.json ${D}/home/root/olli-device-sdk/conf/olli-iviet-0ce2dda01741.json
	install -m 0775 ${WORKDIR}/git/conf/agent.py ${D}/home/root/olli-device-sdk/conf/agent.py

	install -m 0644 ${WORKDIR}/olli-device-sdk.service ${D}${systemd_unitdir}/system/olli-device-sdk.service

	install -m 0775 ${WORKDIR}/git/AdjustVolume/amixer.js ${D}/home/root/olli-device-sdk/AdjustVolume/amixer.js
	install -m 0775 ${WORKDIR}/git/Apps/app.js ${D}/home/root/olli-device-sdk/Apps/app.js

	install -m 0775 ${WORKDIR}/git/AudioPlayer/bluePlayer.js ${D}/home/root/olli-device-sdk/AudioPlayer/bluePlayer.js
	install -m 0775 ${WORKDIR}/git/AudioPlayer/bluetooth.js ${D}/home/root/olli-device-sdk/AudioPlayer/bluetooth.js
	install -m 0775 ${WORKDIR}/git/AudioPlayer/webPlayer.js ${D}/home/root/olli-device-sdk/AudioPlayer/webPlayer.js
	install -m 0775 ${WORKDIR}/git/AudioPlayer/mpg123.js ${D}/home/root/olli-device-sdk/AudioPlayer/mpg123.js

	install -m 0775 ${WORKDIR}/git/i2c-control/ioctl.js ${D}/home/root/olli-device-sdk/i2c-control/ioctl.js
	install -m 0775 ${WORKDIR}/git/i2c-control/buffers.js ${D}/home/root/olli-device-sdk/i2c-control/buffers.js
	install -m 0775 ${WORKDIR}/git/i2c-control/command.js ${D}/home/root/olli-device-sdk/i2c-control/command.js

	install -m 0775 ${WORKDIR}/git/ContextManager/mp_events.js ${D}/home/root/olli-device-sdk/ContextManager/mp_events.js
	install -m 0775 ${WORKDIR}/git/ContextManager/music_player.js ${D}/home/root/olli-device-sdk/ContextManager/music_player.js

	install -m 0775 ${WORKDIR}/git/wakeword/wakeword.js ${D}/home/root/olli-device-sdk/wakeword/wakeword.js


	install -m 0775 ${WORKDIR}/git/MediaPlayer/playurlStream.js ${D}/home/root/olli-device-sdk/MediaPlayer/playurlStream.js

	install -m 0775 ${WORKDIR}/git/Network/wifi.js ${D}/home/root/olli-device-sdk/Network/wifi.js
	install -m 0775 ${WORKDIR}/git/Network/wifi_list.json ${D}/home/root/olli-device-sdk/Network/wifi_list.json
	install -m 0775 ${WORKDIR}/git/Network/index.html ${D}/home/root/olli-device-sdk/Network/index.html

	install -m 0775 ${WORKDIR}/git/event.js ${D}/home/root/olli-device-sdk/event.js
	install -m 0775 ${WORKDIR}/git/package.json ${D}/home/root/olli-device-sdk/package.json

	oe_runnpm --prefix ${WORKDIR}/git/ install

	cp -R ${WORKDIR}/git/node_modules ${D}/home/root/olli-device-sdk
	rm -R ${D}/home/root/olli-device-sdk/node_modules/put/test
	cp -R ${WORKDIR}/git/Sounds ${D}/home/root/olli-device-sdk

}

SYSTEMD_SERVICE_${PN} = "olli-device-sdk.service "

FILES_${PN} += " \
				/home/root/olli-device-sdk/* \
				${systemd_unitdir}/system/olli-device-sdk.service \
				"

FILES_${PN}-staticdev += " /home/root/olli-device-sdk/node_modules/* "
