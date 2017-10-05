DESCRIPTION = "mpg321 is a replacement for mpg123, a very popular command-line mp3 player."
SECTION = "console/multimedia"
DEPENDS = "libmad libao"
LICENSE = "LGPLv2.1"
HOMEPAGE = "http://mpg321.sourceforge.net/"
LIC_FILES_CHKSUM = "file://README;md5=24c2d02e37f2a0ebeb2e714469caf50c"
RCONFLICTS_${PN} = "mpg123"
RREPLACES_${PN} = "mpg123"
PR = "r1"

SRC_URI = "file://mpg321-0.2.10.tar.gz \
           file://libao.m4.patch"

inherit autotools

EXTRA_OECONF="--with-ao-includes=${STAGING_INCDIR} --with-ao-libraries=${STAGING_LIBDIR}"
