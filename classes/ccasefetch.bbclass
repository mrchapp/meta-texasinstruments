# This fetcher class requires the following:
# ${CCASE_SPEC} -		A ClearCase config spec
# ${CCASE_PATHFETCH} -		The elements (files and directories) to fetch
# ${CCASE_PATHCOMPONENT} -	The very last part of the PATH in ClearCase
#				(e.g. "gstreamer" in
#				/vobs/wtbu/OMAPSW_L/mmframework/libs/gstreamer)
# ${CCASE_PATHCOMPONENTS} -	The number of path components to strip. E.g.:
#				5 for {vobs, wtbu, OMAPSW_L, mmframework, libs}
#				leaving just "gstreamer" when untarring.
# ${CCASE_CONTENTPKG} -		A file listing the content that will conform
#				the tarball.
#
# The script (cfetchcc-get.sh) needs to be available in the system.
# Get it from the ccfetch-20090122.tar.gz package.
##
# The config spec must change new-lines with '%' (percentage signs):
#   CCASE_SPEC = "\
#    element /vobs/wtbu/OMAPSW_L/mmframework MMFRAMEWORK_REL_4.20%\
#    element * /main/latest%"
# (Don't forget to escape the recipe's new-lines anyway.) Or this too:
#   CCASE_SPEC = "element /vobs/wtbu/OMAPSW_L/mmframework MMFRAMEWORK_REL_4.20%element * /main/latest%"
#
# This class will be improved as time goes by. We don't like the way
# PATHCOMPONENT and PATHCOMPONENTS need to be specified; ideally, just
# the config spec and the path-to-fetch should be required.
#

inherit base

CCASEFETCH_OUTFILE=${DL_DIR}/${PN}-${PV}.tar.gz

SRCREV=${SRCREV_pn-${PN}}

do_fetch_ccase () {
	TAROPTS=
	if [ ! -z "${CCASE_SPEC}" ]; then
		if [ ! -s ${CCASEFETCH_OUTFILE} ]; then

			[ ! -z "${CCASE_CONTENTPKG}" ] && TAROPTS="${TAROPTS} -c ${CCASE_CONTENTPKG}"

			cd ${DL_DIR}
			echo "${CCASE_SPEC}" | tr '%' '\n' | cfetchcc-get.sh ${TAROPTS} ${CCASE_PATHFETCH} > ${CCASEFETCH_OUTFILE}
		else
			echo Package ${PN}-${PV}.tar.gz already downloaded.
		fi
	else
		echo "Required ClearCase config spec is missing."
		exit 1
	fi
}

do_unpack_ccase () {
	TAROPTS=
	if [ ! -z "${CCASE_SPEC}" ]; then
		[ ! -z "${CCASE_PATHCOMPONENTS}" ] && TAROPTS="${TAROPTS} --strip-components ${CCASE_PATHCOMPONENTS}"
		cd ${WORKDIR}
		tar zxf ${CCASEFETCH_OUTFILE} ${TAROPTS}
		mv ${CCASE_PATHCOMPONENT} ${PN}-${PV}
	fi

    chmod +w -R .
}

addtask fetch_ccase after do_fetch before do_unpack
addtask unpack_ccase after do_unpack before do_patch
