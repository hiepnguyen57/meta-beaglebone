SUMMARY = "Python pulsectl"
SECTION = "devel/python"
LICENSE = "LGPLv2.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=f1d10048469ff90123263eb5e214061d"

PYTHON_BASEVERSION = "2.7"
PYTHON_PN = "python"

DEPENDS += "python pulseaudio sox"

SRCNAME = "pulsectl"
PV = "17.7.4"

SRC_URI = " \
    file://pulsectl-17.7.4.tar.gz \
"
S = "${WORKDIR}/${SRCNAME}-${PV}"


inherit setuptools

RDEPENDS_${PN} = "\
    python-core \
    python-re \
    python-io \
    python-netserver \
    python-numbers \
"