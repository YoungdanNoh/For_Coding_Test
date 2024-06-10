select YEAR(DIFFERENTIATION_DATE) as year, 
       (select max(SIZE_OF_COLONY) 
        from ECOLI_DATA 
        where YEAR(DIFFERENTIATION_DATE) = year) - SIZE_OF_COLONY as YEAR_DEV,
       id
from ECOLI_DATA
order by YEAR(DIFFERENTIATION_DATE), year_dev
