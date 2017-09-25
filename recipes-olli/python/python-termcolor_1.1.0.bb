SUMMARY = "Python termcolor"
SECTION = "devel/python"
LICENSE = "LGPLv2.1"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=809e8749b63567978acfbd81d9f6a27d"

PYTHON_BASEVERSION = "2.7"
PYTHON_PN = "python"

DEPENDS += "python"

SRCNAME = "termcolor"
PV = "1.1.0"

SRC_URI = " \
    file://termcolor-1.1.0.tar.gz \
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