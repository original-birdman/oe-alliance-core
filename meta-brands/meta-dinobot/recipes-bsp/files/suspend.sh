#!/bin/sh

runlevel=runlevel | cut -d' ' -f2

if [ "$runlevel" != "0" ] ; then
	exit 0
fi

mount -t sysfs sys /sys
echo 0 > /proc/stb/lcd/show_symbols
echo "                   "  > /dev/dbox/oled0
/usr/bin/hihalt
