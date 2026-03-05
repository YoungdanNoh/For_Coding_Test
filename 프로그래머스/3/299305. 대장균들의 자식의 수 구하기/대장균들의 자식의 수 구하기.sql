select a.ID as ID, count(b.PARENT_ID) as CHILD_COUNT
from ECOLI_DATA a
left join ECOLI_DATA b
on a.ID = b.PARENT_ID
group by a.ID, b.PARENT_ID
order by a.ID