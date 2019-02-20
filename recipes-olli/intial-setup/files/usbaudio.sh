echo 1 > /sys/class/gpio/gpio67/value
sleep 0.1
echo 0 > /sys/class/gpio/gpio67/value
i2cset -y 2 0x68 0x03 0x45 0x00 i
