require u-boot.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README;md5=4ca38b099eee9d15d8ab9b9730d9c0b2"

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-olli-2016.11:"

PV = "2016.11"
PR = "rc3"

COMPATIBLE_MACHINE = "beaglebone"

UBOOT_LOCALVERSION = "-olli"

# v2016.11-rc3
SRCREV = "693960fbf9099dbc53276c6510c8c05b395a0ce5"
SRC_URI = " \
    git://git@github.com/olli-ai/u-boot.git;branch=master;protocol=ssh \
"

SPL_BINARY = "MLO"
