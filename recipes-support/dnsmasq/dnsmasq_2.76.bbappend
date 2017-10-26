do_install_append() {
	rm ${D}${systemd_unitdir}/system/dnsmasq.service
	rm -r ${D}${sysconfdir}
	rm -r ${D}${base_libdir}
}

SYSTEMD_SERVICE_${PN} = ""