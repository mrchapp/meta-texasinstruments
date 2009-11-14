#!/bin/sh

if [ -e /dev/input/touchscreen0 ]; then
    TSLIB_TSDEVICE=/dev/input/touchscreen0

    export TSLIB_TSDEVICE
elif [ -e /dev/input/event1 ]; then
    TSLIB_TSDEVICE=/dev/input/event1

    export TSLIB_TSDEVICE
fi

