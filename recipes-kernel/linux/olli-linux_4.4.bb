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

# Add run-time dependency for PM firmware to the rootfs
RDEPENDS_kernel-base_append_ti33x = " amx3-cm3"
RDEPENDS_kernel-base_append_ti43x = " amx3-cm3"

# Add run-time dependency for VPE VPDMA firmware to the rootfs
RDEPENDS_kernel-base_append_dra7xx = " vpdma-fw"

# Add run-time dependency for Goodix firmware to the rootfs
RDEPENDS_kernel-base_append_dra7xx = " goodix-fw"

# Install boot-monitor skern file into /boot dir of rootfs
RDEPENDS_kernel-base_append_keystone = " boot-monitor"

# Install ti-sci-fw into /boot dir of rootfs
RDEPENDS_kernel-base_append_k2g = " ti-sci-fw"

# Add run-time dependency for SerDes firmware to the rootfs
RDEPENDS_kernel-base_append_keystone = " serdes-fw"

# Add run-time dependency for QMSS PDSP firmware to the rootfs
RDEPENDS_kernel-base_append_keystone = " qmss-pdsp-fw"

# Add run-time dependency for NETCP PA firmware to the rootfs
RDEPENDS_kernel-base_append_k2hk = " netcp-pa-fw"
RDEPENDS_kernel-base_append_k2e = " netcp-pa-fw"
RDEPENDS_kernel-base_append_k2l-evm = " netcp-pa-fw"

# Add run-time dependency for PRU Ethernet firmware to the rootfs
RDEPENDS_kernel-base_append_am57xx-evm = " prueth-fw"
RDEPENDS_kernel-base_append_am437x-evm = " prueth-fw"
RDEPENDS_kernel-base_append_am335x-evm = " prueth-fw"
RDEPENDS_kernel-base_append_k2g = " prueth-fw"

# Default is to package all dtb files for ti33x devices unless building
# for the specific beaglebone machine.
KERNEL_DEVICETREE_beaglebone = " \
	am335x-boneblack.dtb \
	am335x-bonegreen-wireless.dtb \
    am335x-olli-smartspeaker.dtb \
 "

FILESEXTRAPATHS_prepend := "${THISDIR}/olli-linux-4.4:"

S = "${WORKDIR}/git"

SRCREV = "503b5a97e9d4fc6b31e94ca8efe8e6d114a2dc7a"
PV = "4.4.30"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
PR = "r1"

BRANCH = "master"
KERNEL_GIT_URI = "git://git@github.com/olli-ai/olli-kernel-src.git"
KERNEL_GIT_PROTOCOL = "ssh"
SRC_URI += "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH} \
            file://defconfig"