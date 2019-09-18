# Meta-beaglebone
by hoahiepk10@gmail.com

Version: 1.0.1 (02/12/2019)

## Yocto Info

The yocto version is 2.2.1 the [morty] branch

The 4.4.30 Linux kernel comes from the olli-linux repository.

The u-boot version is 2016.11

The root file system base on `meta-ti` layer

Device tree binatries are generated and installed that support:

1. Beaglebone black (am335x-boneblack.dtb)
2. Beaglebone green wireless (am335x-bonegreen-wireless.dtb)
3. Olli Smart Speaker (am335x-olli-smartspeaker.dtb)

## Ubuntu Setup

The Ubuntu version is 16.04 64-bit servers for builds. Older version should work.

* You will need at least the following packages installed

```
sudo apt-get install build-essential chrpath diffstat gawk git libncurses5-dev pkg-config subversion texi2html texinfo
```

* For 16.04 you also need to install python 2.7 package that the Yocto 2.2 branch requires
```
 python2.7
```
And then create a link for it in `/usr/bin`

```
# sudo ln -sf /usr/bin/python2.7 /usr/bin/python
```

* For all version of Ubuntu, you should change the default Ubuntu shell from `dash` to `bash` by running this command from a shell

```
sudo dpkg-reconfigure dash
```
Choose `No`to dash when prompted

## Clone the repository

### Create the `yocto_release` folder to build

```
mkdir yocto_release
cd yocto_release
export WORK=`pwd`
```

* First the main Yocto project `poky` repository

```
cd $WORK
git clone git://git.yoctoproject.org/poky.git
cd poky
git checkout -b morty 7d5822bf4cb2089687c3c9a908cf4a4ef4e9a53a
if Ubuntu 18.04, you will checkout at: 
git checkout -b morty bfea1efa7b6e5abceac30da937adea4a2fa8d8d3
```

* Then the `meta-openembedded` repository
```
cd $WORK
git clone git://git.openembedded.org/meta-openembedded
cd meta-openembedded
git checkout -b morty fe5c83312de11e80b85680ef237f8acb04b4b26e
if Ubuntu 18.04, you will checkout at: 
git checkout -b morty 997caf9146cd3797cd054e2adebd1fbb4df91911
```

### Clone the `meta-nodejs` repository

```
~$ cd $WORK
git clone https://github.com/imyller/meta-nodejs.git
cd meta-nodejs         
git checkout -b morty eec531e97a17bfd406f3bf76dee4057dcf5286a4
```
### Clone the `meta-beaglebone` repository

```
cd $WORK
git clone git@github.com:hiepnguyen57/meta-beaglebone.git
```
## Building

### Initialize the build directory

Much of the following are only the conventions that I use. All of the paths to the meta-layers are configurable.

Use the Yocto environment script `oe-init-build-env` like this passing in the path to the build directory

```
cd $WORK
source poky/oe-init-build-env build
```

The Yocto environment script will create the build directory if it does not already exist.

### Customize the configuration files

There are some sample configuration files in the `meta-olli/conf` directory.

Copy them to the `build/conf` directory:

```
cp ../meta-olli/conf/local.conf conf/local.conf
cp ../meta-olli/conf/bblayers.conf conf/bblayers.conf
```

### Run the build

You need to source the Yocto environment into your shell before you can `use bitbake`. The `oe-init-build-env` will not overwrite your customized conf files.

```
### Shell environment set up for builds. ###

You can now run 'bitbake <target>'

Common targets are:
    core-image-minimal
    core-image-sato
    meta-toolchain
    meta-ide-support

You can also run generated qemu images with a command like 'runqemu qemux86'

```

This is one custom images available in the meta-olli layers. The recipes for the images can be found in `meta-olli/images/`:

* core-olli-image.bb

To build the `core-olli-image` run the following command

```
bitbake core-olli-image
```

## Copying the binaries to eMMC on Mainboard

After the build completes, the bootloader, kernel and rootfs image files can be found in `<TMPDIR>/deploy/images/beaglebone/`.

The `meta-olli/scipts` directory has some helper scripts to format and copy the files to a eMMC flash memory.

### usb_flasher

This binary will boot the FIT image to external RAM on mainboard through usb port and mount eMMC flash into PC Linux.

Run `usb_flasher` by command:

```
cd $WORK/meta-olli/scripts/booting
sudo ./usb_flasher

```

### mk2parts.sh

This script will partition an eMMC with the minimal 2 partitions required for the boards.

Power on the mainboard and switch to usb boot by press `boot configuration` button.

`lsblk` is convenient for finding the eMMC card.

For exmple:
```$ lsblk 
NAME   MAJ:MIN RM   SIZE RO TYPE MOUNTPOINT
sdb      8:16   0 931,5G  0 disk 
├─sdb2   8:18   0 685,6G  0 part 
└─sdb1   8:17   0 245,9G  0 part 
sdc      8:32   0   3,7G  0 disk 
├─sdc2   8:34   0   3,6G  0 part /media/dark/ROOT
└─sdc1   8:33   0    64M  0 part /media/dark/BOOT
sda      8:0    0 232,9G  0 disk 
├─sda2   8:2    0 224,5G  0 part /
├─sda3   8:3    0   7,9G  0 part [SWAP]
└─sda1   8:1    0   512M  0 part /boot/efi
```

Mainboard mounted at `sdc` on this Linux PC.

#### BE CAREFULY with this script. It will format any disk on your workstation

```
cd $WORK/meta-olli/scripts$
sudo ./mk2part.sh sdc

```

You only have to format the eMMC flash memory once.

### copy_boot.sh

This script copies the bootloader(MLO and u-boot) to the boot partition of the eMMC flash memory.

This script also copies a `uEnv.txt` file to the boot partition if it find one in either.

```
<TMPDIR>/deploy/images/beaglebone/
```
or in the local directory where the script is run from.

#### /media/card ####

You will need to create a mount point on your workstation for the copy script to use.

```
sudo mkdir /media/card
```
You only have to create this directory once.


This script need to know the TMPDIR to find the binaries. It looks for an environment variable called OETMP.

So, you must export this environment variable before running `copy_boot.sh`

```
cd $WORK/meta-olli/scripts$
export OETMP=$WORK/build/tmp

```

Then run the `copy_boot.sh` script passing the location of eMMC flash memory.

```
cd $WORK/meta-olli/scripts$
./copy_boot.sh sdx

```

This script should be run very fast.

### copy_rootfs.sh

This script copies the zImage kernel, the device tree binaries and the rest of the operating system to the root file system partition of the eMMC flash.

Here's an example of how to run `copy_rootfs.sh`:

```

cd $WORK/meta-olli/scripts$
./copy_rootfs.sh sdx

```

### bring_up.sh

This script includes all the functions listed above. You must power on the mainboard, press the "boot configuration" button to boot from usb. It will call usb_flasher to boot FIT image on external RAM. After mounting the eMMC flash memory into PC linux. Then, copies bootloader, zImage, device tree and rootfile system to the partition of the eMMC.

This cripts need to OETMP path to yocto direction. Type command below:

```
cd $WORK/meta-olli/scripts/booting
sudo ./bring_up.sh $WORK/build/tmp 

```
