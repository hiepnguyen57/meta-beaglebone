DESCRIPTION = "mpg321 is a replacement for mpg123, a very popular command-line mp3 player."
SECTION = "console/multimedia"
DEPENDS = "libmad libao"
LICENSE = "LGPLv2.1"
HOMEPAGE = "http://mpg321.sourceforge.net/"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
RCONFLICTS_${PN} = "mpg123"
RREPLACES_${PN} = "mpg123"
PR = "r1"

SRC_URI = "file://mpg321-0.3.2.tar.gz"
inherit autotools

EXTRA_OECONF="--with-ao-includes=${STAGING_INCDIR} --with-ao-libraries=${STAGING_LIBDIR}"
