select distinct ID, EMAIL, FIRST_NAME, LAST_NAME
from SKILLCODES as A, DEVELOPERS as B
where SKILL_CODE & A.CODE = A.CODE
and (NAME = 'Python' or NAME = 'C#')
order by ID