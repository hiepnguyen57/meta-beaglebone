SUMMARY = "this is a summary"
DESCRIPTION = "this is a description "
LICENSE = "Olli"
LIC_FILES_CHKSUM = "file://README.md;md5=00a815132bf7705aedd88242f1ce17b7"
HOMEPAGE = "https://github.com/olli-ai/WakeWord"
SRCREV = "4681c7a554009b3e7ff0c7d45e61a796eed63cc9"
SRC_URI = "git://git@github.com/olli-ai/WakeWord.git;protocol=ssh;branch=c-dev"
DEPENDS = " glib-2.0 dbus portaudio-v19 portaudio-v19 "
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
	install -m 0755 ${WORKDIR}/git/src/wakeup_demo ${D}${bindir}/
}

FILES_${PN} += "${bindir}/* ${libdir}/libf77blas.so.3"
FILES_${PN}-conf = "${sysconfdir}"