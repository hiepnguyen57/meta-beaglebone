SUMMARY = "A portable audio library"
SECTION = "libs/multimedia"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=26107732c2ab637c5710446fcfaf02df"
DEPENDS += " alsa-lib "
PV = "v190600"

SRC_URI = "file://portaudio.tar.gz \
           "
SRC_URI[md5sum] = "a295a0e73392a34119cb4b548db704fe"
SRC_URI[sha256sum] = "afa3145e52c23ee32fd7f89a98dd2eb754423b22d4299ef8476293fce586f766"

S = "${WORKDIR}/portaudio"

inherit autotools pkgconfig
DISABLE_STATIC = ""
EXTRA_OECONF = "--without-jack --without-oss --with-alsa --prefix=/usr --with-pic"

do_compile_append () {
	sed -i '40s:src/common/pa_ringbuffer.o::g' Makefile
	sed -i '40s:$: src/common/pa_ringbuffer.o:' Makefile
}

do_install_append () {
	install -d ${D}${includedir}
	install -m 644 ${WORKDIR}/portaudio/src/common/pa_ringbuffer.h ${D}${includedir}
	install -m 644 ${WORKDIR}/portaudio/src/common/pa_util.h ${D}${includedir}
}

RPROVIDES_${PN}-dev = " portaudio-v19-dev portaudio-v19-staticdev "
FILES_${PN}-dev += "${includedir}/* "
