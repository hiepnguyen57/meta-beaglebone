DESCRIPTION = "Alsa Drivers"
SECTION = "base"
LICENSE = "GPLv2+"

SRC_URI = "ftp://ftp.alsa-project.org/pub/driver/alsa-driver-${PV}.tar.bz2 "
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"
inherit autotools module
DEPENDS += "fakeroot-native"

EXTRA_OECONF = "--with-sequencer=yes \
	--with-isapnp=no \
	--with-oss=yes \
	--with-kernel=/home/toan/yocto_release/build-console/tmp/work/beaglebone-poky-linux-gnueabi/olli-linux/4.4.30-r1/image/lib/modules/4.4.30/kernel/drivers/sound \
	--with-kernel-version=${KERNEL_VERSION}"

SRC_URI[md5sum] = "30684fbfcc84633df9740ab3f8e6bc97"
SRC_URI[sha256sum] = "e5633ba851dfd43f7d864a6df2cc22bb2c1dd46a17b081990f0a7bccf0c1f473"

do_install[depends] += "virtual/kernel:do_shared_workdir"