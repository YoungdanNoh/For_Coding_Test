select ID, FISH_NAME, LENGTH
from FISH_INFO a, FISH_NAME_INFO b
where a.FISH_TYPE = b.FISH_TYPE
and (a.FISH_TYPE, a.LENGTH) IN (select FISH_TYPE, max(LENGTH)
                               from FISH_INFO
                               group by FISH_TYPE)
order by ID