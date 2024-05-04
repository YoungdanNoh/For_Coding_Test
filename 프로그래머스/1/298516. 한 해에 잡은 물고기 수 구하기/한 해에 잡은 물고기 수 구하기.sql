select count(*) as FISH_COUNT
from FISH_INFO
where DATE(TIME) between '2021-01-01' and '2021-12-31'