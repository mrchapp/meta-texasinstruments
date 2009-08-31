#
# Copyright (C) 2008 Texas Instruments
#

DESCRIPTION = "Tasks for the TI's GStreamer Multimedia Framework"
PR = "r11"

PACKAGES = "\
    task-omap-gst \
    task-omap-gst-libs \
    task-omap-gst-plugins \
    task-omap-gst-apps \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"
ALLOW_EMPTY = "1"

RDEPENDS_task-omap-gst = "\
    task-omap-gst-libs \
    task-omap-gst-plugins \
    task-omap-gst-apps \
    "

RDEPENDS_task-omap-gst-libs = "\
    check	\
    gstreamer	\
    "

RDEPENDS_task-omap-gst-plugins = "\
    gst-plugins-base	\
    gst-plugins-good	\
    gst-plugins-bad	\
    gst-plugins-ugly	\
    gst-omap3		\
    gst-plugin-mpegaudioparse \
    gst-plugin-audiotestsrc \
    gst-plugin-videotestsrc \
    gst-plugin-ffmpegcolorspace \
    gst-plugin-video4linux2 \
    gst-plugin-fbdevsink \
    gst-plugin-qtdemux \
    gst-plugin-asf \
    gst-plugin-wavparse \
    gst-plugin-rtsp \
    gst-plugin-rtp \
    gst-plugin-rtpmanager \
    gst-plugin-amrnb \
    gst-plugin-amrwb \
    gst-plugin-freeze \
    gst-plugin-multifile \
    gst-plugin-gconfelements \
    gst-plugin-aacparse \
    gst-plugin-decodebin2 \
    gst-openmax \
    "

RDEPENDS_task-omap-gst-apps = ""
