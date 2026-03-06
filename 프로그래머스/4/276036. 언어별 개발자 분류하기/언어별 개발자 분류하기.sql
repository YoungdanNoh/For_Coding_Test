with grades as(
    select CASE WHEN (SKILL_CODE & (select sum(SKILLCODES.CODE)
                                    from SKILLCODES
                                    where CATEGORY = 'Front End')) > 0
                      AND
                      (SKILL_CODE & (select SKILLCODES.CODE
                                     from SKILLCODES
                                     where NAME = 'Python')) > 0
                  THEN "A"
                  
                  WHEN SKILL_CODE & (select SKILLCODES.CODE
                                     from SKILLCODES
                                     where NAME = 'C#') > 0 
                  THEN "B"
    
                  WHEN SKILL_CODE & (select sum(SKILLCODES.CODE)
                                     from SKILLCODES
                                     where CATEGORY = 'Front End') > 0 
                  THEN "C"
        END AS GRADE,
        ID, EMAIL
    from DEVELOPERS
)
select *
from grades
where GRADE is not null
order by GRADE, ID
            