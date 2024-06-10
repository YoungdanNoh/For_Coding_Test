select count(*) as FISH_COUNT, FISH_NAME
from FISH_INFO as a, FISH_NAME_INFO as b
where a.FISH_TYPE = b.FISH_TYPE
group by FISH_NAME
order by FISH_COUNT desc