SECTION = "kernel"
DESCRIPTION = "Linux kernel for Olli devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc
#require recipes-kernel/linux/setup-defconfig.inc
require recipes-kernel/linux/cmem.inc
#require recipes-kernel/linux/ti-uio.inc

KERNEL_IMAGETYPE = "zImage"

COMPATIBLE_MACHINE = "beaglebone"

# Pull in the devicetree files into the rootfs
RDEPENDS_kernel-base += "kernel-devicetree"

# Default is to package all dtb files for ti33x devices unless building
# for the specific beaglebone machine.
KERNEL_DEVICETREE_beaglebone = " \
    am335x-recovery.dtb \
    am335x-bonegreen-wireless.dtb \
    am335x-boneblack.dtb \
 "

FILESEXTRAPATHS_prepend := "${THISDIR}/recovery-linux-4.4:"

S = "${WORKDIR}/git"

SRCREV = "8d6b0a5a6902aa5bb3d5686792e1f304a0931faf"
PV = "4.4.30"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
PR = "r1"

BRANCH = "olli-kernel"
KERNEL_GIT_URI = "git://git@github.com/olli-ai/linux-stable-rcn-ee.git"
KERNEL_GIT_PROTOCOL = "ssh"
SRC_URI += "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \
            file://defconfig"
