SUMMARY = "The FDK AAC Codec Library For Android "
HOMEPAGE = "https://github.com/mstorsjo/fdk-aac"
SECTION = "libs/multimedia"
LICENSE = " MIT"
LIC_FILES_CHKSUM = "file://MODULE_LICENSE_FRAUNHOFER;md5=d41d8cd98f00b204e9800998ecf8427e"
SRC_URI = "git://git@github.com/mstorsjo/fdk-aac.git;protocol=https"
SRCREV = "e2e35b82738dc9d5e5229477d49d557cadad4dc7"
SRC_URI[md5sum] = "4515aafb55bc7dddfb7462d2d615a7f2"
SRC_URI[sha256sum] = "38df9f50b37617d48d09f8374952a38ec770939dab1202c321749103950f8192"

inherit autotools pkgconfig bluetooth
DEPENDS += "alsa-lib glib-2.0 ortp "
RDEPENDS_${PN} += " libasound "
S= "${WORKDIR}/git"