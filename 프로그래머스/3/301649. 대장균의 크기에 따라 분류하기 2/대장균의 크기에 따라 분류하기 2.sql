select ID,
    CASE
        WHEN NTILE(4) over(order by SIZE_OF_COLONY desc) = 1 THEN 'CRITICAL'
        WHEN NTILE(4) over(order by SIZE_OF_COLONY desc) = 2 THEN 'HIGH'
        WHEN NTILE(4) over(order by SIZE_OF_COLONY desc) = 3 THEN 'MEDIUM'
        ELSE 'LOW' END
        as COLONY_NAME
from ECOLI_DATA
order by ID