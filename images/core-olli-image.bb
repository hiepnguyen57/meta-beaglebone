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
    bluez-alsa \
    bluez-alsa-staticdev \
    led-service \
 "

KERNEL_EXTRA_INSTALL = " \
    kernel-modules \
 "

WIFI_SUPPORT = " \
    bbgw-bluetooth \
    bluez5 \
    crda \
    iw \
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
    binutils \
    binutils-symlinks \
    coreutils \
    cpp \
    cpp-symlinks \
    diffutils \
    g++ \
    g++-symlinks \
    gettext \
    ldd \
    libstdc++ \
    libstdc++-dev \
    make \
 "

DEV_EXTRAS = " \
    mpg123 \
    sox \
    alsa-utils \
    hostapd \
    atlas-base \
    libsoc \
    glib-2.0 \
    glib-2.0-dev \
    nodejs \
    nodejs-npm \
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
    libusb1-dev \
 "

OLLI_APPS = " \
    cloud-services-manager \
    music-player \
    wakeword \
    reset-button \
 "

IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${KERNEL_EXTRA_INSTALL} \
    ${WIFI_SUPPORT} \
    ${DEV_SDK_INSTALL} \
    ${DEV_EXTRAS} \
    ${EXTRA_TOOLS_INSTALL} \
    ${MQTT} \
 "
