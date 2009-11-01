#!/bin/sh

#This file is needed until we sync up with upstream Poky. Upstream Poky has certain recipes
#which are missing in the svn 5728 version. Since L23.x & L24.x depend on the svn version of Poky,
#and we need latest recipes for building X11, we need to do the below.

#Move fbdev from meta-ti/packages/extra to meta/packages/xorg-driver
mv meta-texasinstruments/packages/extra/xf86-video-fbdev meta/packages/xorg-driver/
mv meta-texasinstruments/packages/extra/xf86-video-fbdev_0.4.0.bb meta/packages/xorg-driver/

#Move omapfb from meta-ti/packages/extra to meta/packages/xorg-driver
mv meta-texasinstruments/packages/extra/xf86-video-omapfb meta/packages/xorg-driver/
mv meta-texasinstruments/packages/extra/xf86-video-omapfb_git.bb meta/packages/xorg-driver/

#Move tslib from meta-ti/packages/extra to meta/packages/xorg-driver
mv meta-texasinstruments/packages/extra/xf86-input-tslib meta/packages/xorg-driver/
mv meta-texasinstruments/packages/extra/xf86-input-tslib_0.0.5.bb meta/packages/xorg-driver/

#Move driver common files from meta-ti/packages/extra to meta/packages/xorg-driver
mv meta-texasinstruments/packages/extra/xorg-driver-common.inc meta/packages/xorg-driver/
mv meta-texasinstruments/packages/extra/xorg-driver-video.inc meta/packages/xorg-driver/
mv meta-texasinstruments/packages/extra/xorg-driver-input.inc meta/packages/xorg-driver/

#Move xorg config file from meta-ti/packages/extra to meta/packages/xorg-xserver
mv meta-texasinstruments/packages/extra/xserver-xf86-config meta/packages/xorg-xserver/
mv meta-texasinstruments/packages/extra/xserver-xf86-config_0.1.bb meta/packages/xorg-xserver/
