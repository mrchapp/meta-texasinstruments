inherit ccasefetch autotools pkgconfig

CCASE_SPEC = "%\
element /vobs/wtbu/OMAPSW_L/mmframework/... MMFRAMEWORK_REL_${PV}%\
element * /main/LATEST%\
"

CCASE_PATHFETCH = "/vobs/wtbu/OMAPSW_L/mmframework/apps/dplayer"
CCASE_PATHCOMPONENT = "dplayer"
CCASE_PATHCOMPONENTS = 5

SRC_URI += "file://update.patch;patch=1"
