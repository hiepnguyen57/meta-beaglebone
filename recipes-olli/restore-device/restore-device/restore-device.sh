#!/bin/bash
echo "========================================================="
echo "    restore device now, please keep cable while doing    "
echo "========================================================="

PART=/mnt/tmp

echo "========================================================="
echo "                     create mount point                  "
echo "========================================================="
mkdir -p ${PART}

echo "========================================================="
echo "                mount root partition                     "
echo "========================================================="
mount /dev/mmcblk1p2 ${PART}

echo "========================================================="
echo "                   removing old rootfs                   "
echo "========================================================="
rm -r ${PART}/*

echo "========================================================="
echo "             extracting rootfs to /mnt/tmp               "
echo "========================================================="
tar -C ${PART} -xvf /opt/backup/core-olli-image-beaglebone.tar.xz && sync

echo "========================================================="
echo "        umount root partition and reboot new OS          "
echo "========================================================="

/usr/bin/led_clear
umount ${PART}
reboot


