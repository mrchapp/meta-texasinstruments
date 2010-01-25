CCASE_SPEC = "#****** DASF ******%\
# SRC%\
element /vobs/wtbu/OMAPSW_DSP/audio/alg/SampleRateConverter/... DSP-DASF-SYSTEM_RLS_${PV}%\
# ModemTaskNode%\
element /vobs/wtbu/OMAPSW_DSP/speech/node/ModemTaskNode/... DSP-DASF-SYSTEM_RLS_${PV}%\
# DASF%\
element /vobs/wtbu/OMAPSW_DSP/system/dasf/... DSP-DASF-SYSTEM_RLS_${PV}%\
# HAL%\
element /vobs/wtbu/OMAPSW_DSP/system/hal/... DSP-DASF-SYSTEM_RLS_${PV}%\
# TMON%\
element /vobs/wtbu/OMAPSW_DSP/system/tmon/... DSP-DASF-SYSTEM_RLS_${PV}%\

#****** SYSTEM ******%\
# Baseimage%\
element /vobs/wtbu/OMAPSW_DSP/system/baseimage/...  DSP-DASF-SYSTEM_RLS_${PV}%\
# Utils%\
element /vobs/wtbu/OMAPSW_DSP/system/utils/...  DSP-DASF-SYSTEM_RLS_${PV}%\
# Build system%\
element /vobs/wtbu/OMAPSW_DSP/make/... DSP-DASF-SYSTEM_RLS_${PV}%\
# A/V Sync Task Node%\
element /vobs/wtbu/OMAPSW_DSP/system/avsync/syncclock/... DSP-DASF-SYSTEM_RLS_${PV}%\
# INST2%\
element /vobs/wtbu/OMAPSW_DSP/system/inst2/... DSP-DASF-SYSTEM_RLS_${PV}%\
element * COMPONENT_ROOT%\
"
