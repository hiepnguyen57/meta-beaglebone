#!/bin/bash

usage(){
    echo -e "\n Usage: ${0} <path to STM32F7 Images destination >\n"
    echo -e "Example: sudo $0 /home/root/Images/olli-stm32f7_firmware.bin"
}

if [ "x${1}" = "x" ]; then
    usage
    exit 0
else
        STM32_IMAGES=${1}
fi

echo "==============================================="
echo "==== Flashing Images into Interal_Flash ======="
echo "==============================================="

#Switch to DFU-MODE
#NRST=0
echo 0 > /sys/class/gpio/gpio66/value
#BOOT=1
echo 1 > /sys/class/gpio/gpio61/value
#NRST=1
echo 1 > /sys/class/gpio/gpio66/value
sleep 1

#Flashing Image
 /usr/local/bin/dfu-util -a 0 -s 0x08000000:leave -D ${STM32_IMAGES}

#Reset MCU
#BOOT=0
echo 0 > /sys/class/gpio/gpio61/value
#NRST=0
echo 0 > /sys/class/gpio/gpio66/value
#NRST=1
echo 1 > /sys/class/gpio/gpio66/value

echo "Have Fun !!! Enjoy that !!!"
