SUMMARY = "A small image just capable of allowing a device to boot."

IMAGE_INSTALL = "packagegroup-core-boot"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

KERNEL_RESTORE = " \
    kernel-modules \
    restore-device \
 "
IMAGE_INSTALL += " \
    ${KERNEL_RESTORE} \
 "
