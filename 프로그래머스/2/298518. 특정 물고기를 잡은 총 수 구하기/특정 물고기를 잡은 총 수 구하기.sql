select count(*) as FISH_COUNT
from FISH_INFO a, FISH_NAME_INFO b
where a.FISH_TYPE = b.FISH_TYPE
and FISH_NAME IN ('BASS', 'SNAPPER')