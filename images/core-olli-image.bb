SUMMARY = "A small image just capable of allowing a device to boot."

IMAGE_INSTALL = "packagegroup-core-boot ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

CORE_OS = " \
    openssh \
    iptables \
    dhcp-server \
    rpm \
 "

KERNEL_EXTRA_INSTALL = " \
    kernel-modules \
 "

WIFI_SUPPORT = " \
    bbgw-bluetooth \
    bluez5 \
    crda \
    iw \
    pulseaudio \
    pulseaudio-server \
    wlconf \
    wl18xx-fw \
    wireless-tools \
    wpa-supplicant \
 "

DEV_SDK_INSTALL = " \
    pkgconfig \
    opkg \
    autoconf \
    automake \
    avahi-daemon \
    bash-completion \
    dbus-dev \
    dbus-glib \
    file \
    flex \
    gcc \
    gcc-symlinks \
    gdb \
    git \
    hdparm \
    python-dev  \
    python-modules \
    python3-modules \
    python-dbus \
    python-pygobject \
    python-enum34 \
    python-termcolor \
    python-pulsectl \
 "

DEV_EXTRAS = " \
    ffmpeg \
    mpg321 \
    sox \
    alsa-utils \
    hostapd \
    atlas-base \
 "

EXTRA_TOOLS_INSTALL = " \
    bc \
    bison \
    ca-certificates \
    connman \
    connman-client \
    curl \
    dosfstools \
    i2c-tools \
    info \
    htop \
    ncurses \
    ppp \
    procps \
    rsync \
    screen \
    sudo \
    usbutils \
    vim \
    wget \
 "

MQTT = " \
    libnss-mdns \
    libtool \
    lsof    \
    lzop    \
    libusb1 \
 "
OLLI_APPS = " \
    cloud-services-manager \
    music-player \
    nodejs \
    nodejs-npm \
    wakeword \
    libao \
    libao-plugin-libpulse \
    libao-plugin-libalsa \
 "
IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${KERNEL_EXTRA_INSTALL} \
    ${WIFI_SUPPORT} \
    ${DEV_SDK_INSTALL} \
    ${DEV_EXTRAS} \
    ${EXTRA_TOOLS_INSTALL} \
    ${MQTT} \
    ${OLLI_APPS} \
 "
