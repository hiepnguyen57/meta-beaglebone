require u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-olli-2016.11:"

PV = "2016.11"
PR = "rc3"

COMPATIBLE_MACHINE = "beaglebone"

UBOOT_LOCALVERSION = "-olli"

# v2016.11-rc3
SRCREV = "d8bdfc80da39211d95f10d24e79f2e867305f71b"
SRC_URI = " \
    git://git.denx.de/u-boot.git;branch=master;protocol=git \
    file://0001-supported-GPIO-button-for-restoring.patch \
"

SPL_BINARY = "MLO"
