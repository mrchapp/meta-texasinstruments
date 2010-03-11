# This is a class for accessing gforge git trees internally at TI
#
# This fetcher class requires the following:
# ${SRC_GFORGE} - Gforge git tree e.g.:
#                 SRC_GFORGE = "ssh://${USER}@gforge01.dal.design.ti.com/gitroot/gfx_l23_ddk"
# Prerequisites: TI internal access to gforge configured. 

inherit base

GFORGEFETCH_OUTFILE=${DL_DIR}/${PN}-${PV}.tar.gz
GFORGEFETCH_PROJECT=`echo ${SRC_GFORGE} | awk -F@ '{print $2}' | sed 's/\//-/g'`
GFORGEFETCH_REF=${DL_DIR}/git/${PN}-${GFORGEFETCH_PROJECT}

do_fetch_gforge () {
  if [ ! -z "${SRC_GFORGE}" ]; then
    if [ ! -s ${GFORGEFETCH_OUTFILE} ]; then
      if [ ! -d ${GFORGEFETCH_REF} ]; then
        echo "${GFORGEFETCH_REF} fetched for the first time"
        echo "If it fails, remove dir: ${GFORGEFETCH_REF}, before to retry"
	git clone ${SRC_GFORGE} ${GFORGEFETCH_REF}
      else
        cd ${GFORGEFETCH_REF}
	echo "Synchronization with remote on ${GFORGEFETCH_REF}"
        echo "If it fails, remove dir: ${GFORGEFETCH_REF}, before to retry"
	# Required for git version 1.5.4.4 - if no echo this step fails 
	# because git remote update return 1 if no update
        (git remote update; echo "remote update done")
      fi
      
      echo "Create source tarball"
      cd ${GFORGEFETCH_REF}
      git checkout -b work ${SRCREV}
      tar zcf ${GFORGEFETCH_OUTFILE} `ls`
      git branch -f last_work
      git checkout last_work
      git branch -D work
    else
      echo "Package ${PN}-${PV}.tar.gz already downloaded."
    fi
  else
    echo "Required SRC_GFORGE is missing."
    exit 1
  fi
}

do_unpack_gforge () {
  if [ ! -z "${SRC_GFORGE}" ]; then
    mkdir -p ${WORKDIR}/git
    cd ${WORKDIR}/git
    tar zxf ${GFORGEFETCH_OUTFILE}
  fi
}

addtask fetch_gforge after do_fetch before do_unpack
addtask unpack_gforge after do_unpack before do_patch
