select a.ID, count(b.PARENT_ID) as CHILD_COUNT
from ECOLI_DATA a LEFT JOIN ECOLI_DATA b
on a.ID = b.PARENT_ID
group by a.ID
order by a.ID