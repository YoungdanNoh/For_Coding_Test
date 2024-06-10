select count(*) as FISH_COUNT
from FISH_INFO as a, FISH_NAME_INFO as b
where a.FISH_TYPE = b.FISH_TYPE
and b.FISH_NAME in ('BASS', 'SNAPPER')