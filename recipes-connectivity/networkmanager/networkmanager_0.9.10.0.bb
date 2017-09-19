SUMMARY = "NetworkManager"
SECTION = "net/misc"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=cbbffd568227ada506640fe950a4823b \
                    file://libnm-util/COPYING;md5=1c4fa765d6eb3cd2fbd84344a1b816cd \
                    file://docs/api/html/license.html;md5=0d1305b159607a08b151c9a84479729a \
"

DEPENDS = "intltool-native libnl dbus dbus-glib dbus-glib-native libgudev util-linux libndp libnewt polkit ppp libsoup-2.4 nss iptables dnsmasq"

inherit autotools gnomebase gettext systemd bluetooth bash-completion vala gobject-introspection

SRC_URI = " \
    ${GNOME_MIRROR}/NetworkManager/${@gnome_verdir("${PV}")}/NetworkManager-${PV}.tar.xz \
    file://0001-Debian-specific-tweaks-for-NetworkManager-systemd-se.patch \
    file://0002-Force-online-state-with-unmanaged-devices.patch \
    file://0003-Don-t-setup-Sleep-Monitor-if-not-booted-with-systemd.patch \
    file://0004-Use-symlinks-for-nmtui.patch \
    file://0005-Mark-virtual-ethernet-interfaces-as-unmanaged.patch \
    file://0006-tui-fix-Wi-Fi-section-of-nmtui-connect-list-in-non-U.patch \
    file://0007-core-fix-checks-for-default-routes-by-comparing-the-.patch \
    file://0008-Use-the-correct-path-when-calling-dnssec-trigger-scr.patch \
    file://0009-Support-building-against-libsystemd-library.patch \
    file://0010-tui-fix-requesting-and-displaying-secrets.patch \
    file://0011-tui-fix-updating-of-NmtPasswordFields-passwords-bgo-.patch \
    file://0012-fix-arping-path.patch \
    file://0013-fix-dhclient-abnormal-exit-due-to-SIGPIPE.patch \
    file://0014-log-DHCLIENT-exit-status-better.patch \
    file://0015-core-don-t-override-external-route-metrics.patch \
    file://0016-core-tell-systemd-to-restart-NetworkManager.service-.patch \
    file://0017-Check-at-runtime-whether-to-start-ModemManager.patch \
    file://0018-core-do-not-assert-when-a-device-is-enslaved-externa.patch \
    file://0019-Don-t-make-NetworkManager-D-Bus-activatable.patch \
    file://0020-Don-t-block-network.target-on-NetworkManager-wait-on.patch \
    file://NetworkManager.conf \
"
SRC_URI[md5sum] = "21b9051dbbd6434df4624a90ca9d71b6"
SRC_URI[sha256sum] = "66a88346bb04d4f402540281181340313b2ec433e75aa9d9ea13f31697f9487e"

S = "${WORKDIR}/NetworkManager-${PV}"

EXTRA_OECONF = " \
    --libexecdir=${libdir}/NetworkManager \
    --disable-ifcfg-rh \
    --disable-ifnet \
    --disable-ifcfg-suse \
    --disable-more-warnings \
    --with-nmtui=yes \
    --with-systemdsystemunitdir=${systemd_unitdir}/system \
    --enable-introspection  \
    --enable-concheck \
    --with-crypto=nss \
    --enable-dependency-tracking \
    --with-iptables=${sbindir}/iptables \
"

PACKAGECONFIG ??= " ifupdown dhclient dnsmasq netconfig ppp \
    ${@bb.utils.contains('DISTRO_FEATURES','systemd','systemd','consolekit',d)} \
    ${@bb.utils.contains('DISTRO_FEATURES','wifi','wifi','',d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'bluez5', '', d)} \
"
PACKAGECONFIG[systemd] = " \
    --with-systemdsystemunitdir=${systemd_unitdir}/system --with-session-tracking=systemd --enable-polkit --with-suspend-resume=systemd , \
    --without-systemdsystemunitdir, \
    polkit \
"
PACKAGECONFIG[bluez5] = "--enable-bluez5-dun,--disable-bluez5-dun,bluez5"
# consolekit is not picked by shlibs, so add it to RDEPENDS too
# PACKAGECONFIG[consolekit] = "--with-session-tracking=consolekit,,consolekit,consolekit"
PACKAGECONFIG[concheck] = "--with-libsoup=yes,--with-libsoup=no,libsoup-2.4"
PACKAGECONFIG[modemmanager] = "--with-modem-manager-1=yes,--with-modem-manager-1=no,modemmanager"
PACKAGECONFIG[ppp] = "--enable-ppp,--disable-ppp,ppp,ppp"
# Use full featured dhcp client instead of internal one
PACKAGECONFIG[gnutls] = "--with-crypto=gnutls,,gnutls libgcrypt"
PACKAGECONFIG[dhclient] = "--with-dhclient=${base_sbindir}/dhclient,,,dhcp-client"
PACKAGECONFIG[dnsmasq] = "--with-dnsmasq=${bindir}/dnsmasq"
PACKAGECONFIG[wifi] = "--enable-wifi=yes,--enable-wifi=no,wireless-tools,wpa-supplicant wireless-tools"
PACKAGECONFIG[ifupdown] = "--enable-ifupdown,--disable-ifupdown"
PACKAGECONFIG[netconfig] = "--with-netconfig=yes,--with-netconfig=no"


do_compile_prepend() {
        export GIR_EXTRA_LIBS_PATH="${B}/libnm-util/.libs"
}

do_install_append() {
    install -d ${D}${sysconfdir}/NetworkManager
    install -m 0775 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/NetworkManager.conf
}

FILES_${PN} = " \
    /run/* \
    ${datadir}/dbus-1 \
    ${datadir}/dbus-1/system-services \
    ${datadir}/dbus-1/system-services/org.freedesktop.nm_dispatcher.service \
    ${libdir}/NetworkManager/* \
    ${libdir}/pppd/2.4.5/* \
    ${systemd_unitdir}/system/* \
    ${sysconfdir}/* \
    ${localstatedir}/* \
    ${datadir}/* \
    ${libdir}/* \
    ${bindir}/* \
    ${base_libdir}/udev \
    ${base_libdir}/udev/rules.d/* \
    ${sbindir}/* \
"

SYSTEMD_SERVICE_${PN} = "NetworkManager.service NetworkManager-dispatcher.service NetworkManager-wait-online.service "