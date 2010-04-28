#!/bin/sh
#
# V1.0
# Last update: 2010-04-28
#
#-------------------------------------------------------------------------
# History:
# V1.0: initial version
#
#-------------------------------------------------------------------------
#
# Location: meta-texasinstruments/scripts/clean_ti_build.sh
#
# README - SHORT DESCRIPTION
#
# This script must be executed in a prompt where the "poky-init-build-env"
# has been sourced.
# This script will clean all TI components + all TI tasks.
# It can be used to clean an existing build environment before to switch
# to a new meta-ti version in a way to rebuild only TI code and get the
# result of the build quicker.
# omap-image-sato can be then rebuilt safely.
#
# This tool can be used only on L23.I3.6 and onward due to dependencies on
# recipes.
#
#-------------------------------------------------------------------------


should_I_stay()
{
# This function is dedicated to ask user if he wants to continue
  DEFLT="N";
  echo "Do you want to continue (y/n)? [${DEFLT}]: $C"
        RDVAR=$DEFLT
        read RDVAR

  case $RDVAR in
                "") RDVAR=$DEFLT;;
  esac

  ANSWER=$RDVAR
  case $ANSWER in
           Y|y) ;;
           *)   echo "Script ended, no action performed."
           exit 0 ;;
  esac
}

if [ -z "$OEROOT" ]; then echo "ERROR: OEROOT is empty, no Poky build can be run in this prompt"; exit 0; fi

echo "++ Start cleaning process ++"

# Save current location
where_i_am=`pwd`

# Go in meta-texasinstruments from build environment
cd ${OEROOT}/meta-texasinstruments/packages

# List TI recipes
LIST_RECIPES=`find \
btfm \
gfx \
gst-* \
gstreamer \
libgoo \
linux \
meta \
shot \
tidspbridge \
tiopenmax \
tisocketnodes \
uboot \
wlan \
xload \
-name "*.bb" | awk -F_ '{$NF =""; print $0}' | sort -u | awk -F/ '{print $NF}'`

# List TI tasks
LIST_TASKS=`find \
tasks \
-name "*.bb" | sed 's/.bb//g' | awk -F/ '{print $NF}'`

# Come back to previous location
cd $where_i_am

# Inform user about the commands
echo "The script will clean following build environment: $OEROOT"
echo "Following command will be launched:"
echo "bitbake -c clean $LIST_RECIPES"
echo "bitbake -c clean $LIST_TASKS"

# Prompt user if he wants to quit
should_I_stay

# Execute cleaning commands
bitbake -c clean $LIST_RECIPES
bitbake -c clean $LIST_TASKS

echo "++ End ++"
exit 0
