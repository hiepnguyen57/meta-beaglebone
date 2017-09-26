SUMMARY = "Automatically Tuned Linear Algebra Software, generic static"
SECTION = "libs/multimedia"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;md5=270e40014eb6849b6ea532508270da75"
DEPENDS+="dpkg-native"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
inherit package_deb
SRC_URI = " \
	file://atlas-base.tar.gz \
	file://libgfortran.so.3 \
	file://README \
"
SRC_URI[md5sum] = "821f99b779df116d0049ed9a1afd8b3b"
SRC_URI[sha256sum] = "be9b265cf5a60e45a1ba072a89e78034cf38a20515487b419bfc3af2583f8edc"


S = "${WORKDIR}"

do_install(){
	install -d ${D}${libdir}/
	install -m 0644 ${WORKDIR}/libgfortran.so.3 ${D}${libdir}/
	${WORKDIR}/atlas-base/install.sh ${WORKDIR}/atlas-base/data ${D}${libdir}/
}

#INSANE_SKIP_${PN}_append = "already-stripped"
DISABLE_STATIC = ""
FILES_${PN}-dev += "${libdir}/*.so* ${libdir}/atlas-base/*.so ${libdir}/atlas-base/atlas/*.so "

#FILES_${PN}-dev += " ${libdir}/atlas-base/*.o ${libdir}/atlas-base/*.so  ${libdir}/atlas-base/atlas/*.o ${libdir}/atlas-base/atlas/*.so ${libdir}/pkgconfig/* "
FILES_${PN}-staticdev += " ${libdir}/*.a ${libdir}/atlas-base/atlas/*.a "
ALLOW_EMPTY_${PN} = '1'