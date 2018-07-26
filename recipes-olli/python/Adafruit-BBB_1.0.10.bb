DERIPTION = "Library to provide a cross-platform GPIO interface on the Raspberry Pi and Beaglebone Black using the RPi.GPIO and Adafruit_BBIO libraries"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=048da203deede4993c40ef2299ef30bf"

SRC_URI = " \
    file://Adafruit_BBIO-1.0.10.tar.gz \
"

SRC_URI[md5sum] = "6a0c286bfb5cb613e25a2e0ffa7da9a7"
SRC_URI[sha256sum] = "6c3a11f9a84653537e09e2e70311b9d3a6f57d98838b2157b9a65ce0f52b4a85"

PYPI_PACKAGE = "Adafruit_GPIO"
inherit pypi setuptools
