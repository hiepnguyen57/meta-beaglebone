SUMMARY = "WakeWord"
DESCRIPTION = "Wakeword detector using snowboy "
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://README.md;md5=00a815132bf7705aedd88242f1ce17b7"
HOMEPAGE = "https://github.com/olli-ai/WakeWord"
SRCREV = "a1b69f49ba7060b8ec48bc6a7203e183aa8cac28"
SRC_URI = "	\
			git://git@github.com/olli-ai/WakeWord.git;protocol=ssh;branch=c-dev \
			file://alexa.umdl \
			file://common.res \
			file://wakeword.service \
			file://wakeword.conf \
			file://dong.wav \
			"
DEPENDS = " glib-2.0 dbus portaudio-v19 portaudio-v19 atlas-base "
#REDENDS = " libgfortran "
S= "${WORKDIR}/git"

inherit pkgconfig systemd
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
TARGET_CC_ARCH += "${LDFLAGS}"
EXTRA_OEMAKE += "\
    'CC=${CC}' \
    'sysroot=${STAGING_DIR_TARGET}/' \
"

do_compile () {
	oe_runmake -C ${S}/src
}

do_install_append () {
	install -d ${D}${bindir}
	install -d ${D}${datadir}/sounds/olli
	install -d ${D}${sysconfdir}/wakeword/resources
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}${sysconfdir}/dbus-1/system.d

	install -m 0644 ${WORKDIR}/wakeword.conf ${D}${sysconfdir}/dbus-1/system.d
	install -m 0644 ${WORKDIR}/wakeword.service ${D}${systemd_unitdir}/system/wakeword.service 
	install -m 0775 ${WORKDIR}/alexa.umdl ${D}${sysconfdir}/wakeword/resources
	install -m 0775 ${WORKDIR}/common.res ${D}${sysconfdir}/wakeword/resources 
	install -m 0755 ${WORKDIR}/git/src/wakeup_demo ${D}${bindir}/
	install -m 0644 ${WORKDIR}/dong.wav ${D}${datadir}/sounds/olli
}

FILES_${PN} += " \
				${bindir}/* \
				${libdir}/libf77blas.so.3 \
				${systemd_unitdir}/system/wakeword.service \
				${sysconfdir}/dbus-1/system.d/wakeword.conf \ 
				"
FILES_${PN}-conf = " ${sysconfdir} "
SYSTEMD_SERVICE_${PN} = " wakeword.service"