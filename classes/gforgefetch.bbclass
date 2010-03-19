# This is a class for accessing gforge git trees internally at TI
#
# This fetcher class requires the following:
# ${SRC_GFORGE}       - Gforge git tree e.g.:
#                       SRC_GFORGE = "ssh://${USER}@gforge01.dal.design.ti.com/gitroot/gfx_l23_ddk"
#
# Prerequisites: TI internal access to gforge configured. 

inherit base

GFORGEFETCH_PROJECT=`echo ${SRC_GFORGE} | awk -F@ '{print $2}' | sed 's/\//-/g'`
GFORGEFETCH_OUTFILE=${DL_DIR}/${GFORGEFETCH_PROJECT}-${PV}.tar.gz

do_fetch_gforge () {
  if [ ! -z "${SRC_GFORGE}" ]; then
    if [ ! -s ${GFORGEFETCH_OUTFILE} ]; then
      if [ -f ${GFORGEFETCH_OUTFILE}.lock ]; then
        # Wait 15 minutes maximum
	wait=0
	while [ "$wait" -lt "900" ]
	do
	  wait=`expr $wait + 10`
	  sleep 10
	  if [ -f ${GFORGEFETCH_OUTFILE}.lock ]; then
	    echo "Still locked - $wait sec"
	  else
	    break
	  fi
	done
        if [ ! -s ${GFORGEFETCH_OUTFILE} ]; then
          echo "Problem: file ${GFORGEFETCH_OUTFILE} is not available or empty"
          exit 1
        fi
        echo "Unlocked - ${GFORGEFETCH_OUTFILE} is available now."  
      else
        touch ${GFORGEFETCH_OUTFILE}.lock
	git archive --format=tar -v --remote=${SRC_GFORGE} ${SRCREV} | gzip > ${GFORGEFETCH_OUTFILE}_downloading
	mv ${GFORGEFETCH_OUTFILE}_downloading ${GFORGEFETCH_OUTFILE}
	rm -fr ${GFORGEFETCH_OUTFILE}.lock
      fi
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
