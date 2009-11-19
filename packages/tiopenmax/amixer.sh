#!/bin/sh

case $1 in

	-s)
	echo headset mixer config
	amixer cset numid=3 15
	amixer cset numid=7 1
	amixer cset numid=8 1
	amixer cset numid=11 1
	amixer cset numid=12 1
	;;

	-f)
	echo handsfree mixer config
	amixer cset numid=4 23
	amixer cset numid=5 1
	amixer cset numid=6 1
	amixer cset numid=9 1
	amixer cset numid=10 1
	;;

	-a)
	echo all mixer config
	amixer cset numid=3 15
        amixer cset numid=4 23
        amixer cset numid=5 1
        amixer cset numid=6 1
        amixer cset numid=7 1
	amixer cset numid=8 1
        amixer cset numid=9 1
        amixer cset numid=10 1
        amixer cset numid=11 1
        amixer cset numid=12 1

	;;
	*)
	echo "Usage: $0 {-s|-f|--a)"
esac

exit 0

