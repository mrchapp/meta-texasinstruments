CCASE_SPEC = "#****** SYSTEM ******%\
# Baseimage%\
element /vobs/wtbu/OMAPSW_DSP/system/baseimage/... LINUX_RLS_${PV}0%\
element /vobs/wtbu/OMAPSW_DSP/system/utils/... LINUX_RLS_${PV}0%\
# Build system%\
element /vobs/wtbu/OMAPSW_DSP/make/... LINUX_RLS_${PV}0%\
# A/V Sync Task Node%\
element /vobs/wtbu/OMAPSW_DSP/system/avsync/syncclock/... LINUX_RLS_${PV}0%\
# INST2%\
element /vobs/wtbu/OMAPSW_DSP/system/inst2/... LINUX_RLS_${PV}0%\
element * /main/LATEST%\
"
