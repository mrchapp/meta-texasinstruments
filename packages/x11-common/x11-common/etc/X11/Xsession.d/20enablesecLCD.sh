#!/bin/sh

echo 1 > /sys/devices/platform/omapdss/display1/enabled
echo "2lcd" > /sys/devices/platform/omapdss/overlay1/manager
echo 1 > /sys/devices/platform/omapdss/overlay1/enabled

	
matchbox-desktop --display=:0.1 &

START_APPLETS=showdesktop,windowselector
END_APPLETS=clock,battery,systray,startup-notify,notify

matchbox-panel --titlebar --start-applets $START_APPLETS --end-applets $END_APPLETS --display=:0.1 &
matchbox-window-manager -theme Sato -use_desktop_mode decorated -display ":0.1" &


