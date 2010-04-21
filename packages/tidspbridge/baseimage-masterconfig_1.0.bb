PRIORITY = "optional"
DESCRIPTION = "Texas Instruments MPU/DSP Bridge Socket Node compilation."
LICENSE = "LGPL"
PR = "r0"

CCASE_SPEC = "%\
	      element /vobs/wtbu/OMAPSW_Loadbuild/tools/scm/... LINUX_RLS_23.17P1%\
	      element -directory /vobs/wtbu/OMAPSW_Loadbuild/... /main/LATEST%"

CCASE_PATHFETCH = " \
	/vobs/wtbu/OMAPSW_Loadbuild/tools/scm/build_tools \
	/vobs/wtbu/OMAPSW_Loadbuild/tools/scm/RelCfg \
	"
CCASE_PATHCOMPONENT = "scm"
CCASE_PATHCOMPONENTS = "4"

SRC_URI = "file://masterconfig.patch;patch=1"

inherit ccasefetch

do_compile() {
	cd ${S}/build_tools
	perl mcl_parser.pl -fr=Linux_23.x -fn=../RelCfg/Master_Configurable_List.csv -od=badwindows
	mv badwindows\\MasterConfig.h MasterConfig.h 
	mv badwindows\\MasterConfig.inc MasterConfig.inc 
	mv badwindows\\MasterConfig.pl MasterConfig.pl 
	mv badwindows\\MasterConfig.tci MasterConfig.tci
}

do_stage() {
        chmod -R +w ${S}/*
	install -d ${STAGING_INCDIR}/dspbridge/include
	cp -a ${S}/build_tools/MasterConfig.* ${STAGING_INCDIR}/dspbridge/include
}
