CCASE_SPEC = "\
	${@base_contains("DISTRO_FEATURES", "testpatterns", "", "element patterns /main/0", d)}%\

	# OMX Audio%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/aac_dec/inc/OMX_AacDec_Utils.h ACT_X0087450-DSP-LAST-BUFFER-FLAG%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/aac_dec/src/OMX_AacDec_Utils.c ACT_X0087450-DSP-LAST-BUFFER-FLAG%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/aac_dec/src/OMX_AacDecoder.c ACT_X0087450-DSP-LAST-BUFFER-FLAG%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/mp3_dec/inc/OMX_Mp3Dec_Utils.h ACT_X0087450-DSP-LAST-BUFFER-FLAG%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/mp3_dec/src/OMX_Mp3Dec_Utils.c ACT_X0087450-DSP-LAST-BUFFER-FLAG%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/nbamr_dec/inc/OMX_AmrDec_Utils.h ACT_X0087450-NBAMR-UTILS.H-REMOVE-NOISE%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/wma_dec/inc/OMX_WmaDec_Utils.h ACT_X0087450-DSP-LAST-BUFFER-FLAG%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/wma_dec/src/OMX_WmaDec_Utils.c ACT_X0087450-DSP-LAST-BUFFER-FLAG%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/pcm_enc/inc/OMX_PcmEnc_Utils.h ACT_X0087450-PCMENC-UTILS.H-REMOVE-NOISE%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/imaadpcm_dec/inc/OMX_ImaAdpcmDec_Utils.h ACT_X0087450-DSP-LAST-BUFFER-FLAG%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/imaadpcm_enc/inc/OMX_ImaAdpcmEnc_Utils.h ACT_X0087450-IMAADPCMENC-UTILS.H-REMOVE-NOISE%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/wbamr_dec/inc/OMX_WbAmrDec_Utils.h ACT_X0087450-WBAMRDEC-HEADERS-REMOVE-NOISE%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/wbamr_dec/inc/OMX_WbAmrDecoder.h ACT_X0087450-WBAMRDEC-HEADERS-REMOVE-NOISE%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/gsmfr_enc/inc/OMX_GSMFrEnc_Utils.h ACT_X0087450-GSMFRENC-HEADERS-REMOVE-NOISE%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/gsmhr_enc/inc/OMX_GSMHrEnc_Utils.h ACT_X0087450-GSMHRENC-HEADERS-REMOVE-NOISE%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/g711_enc/inc/OMX_G711Enc_Utils.h ACT_X0087450-DSP-LAST-BUFFER-FLAG%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/g722_enc/inc/OMX_G722Enc_Utils.h ACT_X0087450-DSP-LAST-BUFFER-FLAG%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/g722_enc/src/OMX_G722Enc_Utils.c ACT_X0087450-DSP-LAST-BUFFER-FLAG2%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/g723_enc/inc/OMX_G723Enc_Utils.h ACT_X0087450-G723ENC-HEADERS-REMOVE-NOISE%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/g723_enc/inc/OMX_G723Encoder.h ACT_X0087450-G723ENC-HEADERS-REMOVE-NOISE%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/g726_dec/inc/OMX_G726Dec_Utils.h ACT_X0087450-DSP-LAST-BUFFER-FLAG%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/src/openmax_il/g726_enc/inc/OMX_G726Enc_Utils.h ACT_X0087450-DSP-LAST-BUFFER-FLAG%\
	element /vobs/wtbu/OMAPSW_MPU/linux/audio/... LINUX-MMAUDIO_RLS_3.25%\
	
	# OMX Video%\
	element /vobs/wtbu/OMAPSW_MPU/linux/video/... LINUX-MMVIDEO_RLS_3.26-PRE%\
	
	# OMX Image%\
	element /vobs/wtbu/OMAPSW_MPU/linux/image/... LINUX-MMIMAGE_RLS_3.26-PRE%\
	
	# LCML & core%\
	element /vobs/wtbu/OMAPSW_MPU/linux/system/... LINUX-MMSYSTEM_RLS_3.26-PRE%\
	
	# OMX Application%\
	element /vobs/wtbu/OMAPSW_MPU/linux/application/... LINUX-MMAPPLICATION_RLS_3.26-PRE%\
	
	# OMX INST2 utilities%\
    element /vobs/wtbu/OMAPSW_MPU/linux/utilities/src/inst2/... LINUX-MMUTILS_RLS_3.02.03%\

	# ROOT folder & Make files%\
	element /vobs/wtbu/OMAPSW_MPU/linux/... LINUX-MMROOT_RLS_3.20%\
	# special hack needed because root of vob is not labeled:%\
	element /vobs/wtbu/OMAPSW_MPU /main/LATEST%\
	# don't pick up anything that is not labeled%\
	element * /main/0%\
	"

# Workaround for LO convergence
# Disable resource manager support

export OMX_RESOURCEMANAGER_DISABLE=1
