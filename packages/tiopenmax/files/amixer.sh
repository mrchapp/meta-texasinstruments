#!/bin/sh

case $1 in

	-s)
	echo headset mixer config
	amixer cset name='Headset Playback Volume' 15
	amixer cset name='HSDAC Right Playback Switch' 1
	amixer cset name='HSDAC Left Playback Switch' 1
	;;

	-f)
	echo handsfree mixer config
	amixer cset name='Handsfree Playback Volume' 15
	amixer cset name='HFDAC Left Playback Switch' 1
	amixer cset name='HFDAC Right Playback Switch' 1
	;;

	-e)
	echo earphone mixer configuration
	amixer cset name='Earphone Driver Switch' 1
	amixer cset name='Earphone Playback Volume' 12
	;;

	-c)
	echo capture configuration
	amixer cset name='Analog Right Capture Route' 1
	amixer cset name='Analog Left Capture Route' 1
	amixer cset name='Capture Preamplifier Volume' 1
	amixer cset name='Capture Volume' 4
	;;

	-a)
	echo all mixer config
	# HS
	amixer cset name='Headset Playback Volume' 15
	amixer cset name='HSDAC Right Playback Switch' 1
	amixer cset name='HSDAC Left Playback Switch' 1

	# HF
	amixer cset name='Handsfree Playback Volume' 15
	amixer cset name='HFDAC Left Playback Switch' 1
	amixer cset name='HFDAC Right Playback Switch' 1

	# Capture
	amixer cset name='Analog Right Capture Route' 1
	amixer cset name='Analog Left Capture Route' 1
	amixer cset name='Capture Preamplifier Volume' 1
	amixer cset name='Capture Volume' 4

	#Earphone
	amixer cset name='Earphone Driver Switch' 1
	amixer cset name='Earphone Playback Volume' 12
	;;

	*)
	echo "Usage: $0 {-s, headset mixer configuration
		-f,  handsfree mixer configuration
		-e, earphone mixer configuration
		-c, capture config main-mic/sub-mic per default
		-a, all mixer config}"
esac

exit 0

