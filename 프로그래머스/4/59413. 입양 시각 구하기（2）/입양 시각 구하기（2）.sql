-- 코드를 입력하세요
with recursive hours as(
    select 0 as h
    
    union all
    
    select h+1 as h
    from hours
    where h < 23
)
SELECT h as HOUR, count(HOUR(DATETIME)) as COUNT
from hours
left join ANIMAL_OUTS
on hours.h = HOUR(DATETIME)
group by h
order by h