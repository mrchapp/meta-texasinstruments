PRIORITY = "optional"
DESCRIPTION = "Texas Instruments WB-AMR Encoder Socket Node Codec."
PR = "r1"

CCASE_SPEC = "%\
	element /vobs/wtbu/OMAPSW_DSP/... ${SRCREV}%\
	"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_DSP/speech/alg/wbamrenc/100_s_g7222_e_all_1_xx_yy_tesla"
CCASE_PATHCOMPONENT = "wtbu"
CCASE_PATHCOMPONENTS = "1"

inherit ccasefetch

PV = "4.0+cc+${SRCREV}"

do_stage() {
    install -d ${STAGING_BINDIR}/dspbridge/OMAPSW_DSP
    cp -a ${S}/OMAPSW_DSP/* ${STAGING_BINDIR}/dspbridge/OMAPSW_DSP
}
