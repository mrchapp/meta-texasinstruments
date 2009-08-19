CCASE_SPEC = "#****** SYSTEM ******%\
# Baseimage%\
element /vobs/wtbu/OMAPSW_DSP/system/baseimage/... LINUX_RLS_${PV}2RC1%\
element /vobs/wtbu/OMAPSW_DSP/system/utils/... LINUX_RLS_${PV}2RC1%\
# Build system%\
element /vobs/wtbu/OMAPSW_DSP/make/... LINUX_RLS_${PV}2RC1%\
# A/V Sync Task Node%\
element /vobs/wtbu/OMAPSW_DSP/system/avsync/syncclock/... LINUX_RLS_${PV}2RC1%\
# INST2%\
element /vobs/wtbu/OMAPSW_DSP/system/inst2/... LINUX_RLS_${PV}2RC1%\
element * /main/LATEST%\
"
