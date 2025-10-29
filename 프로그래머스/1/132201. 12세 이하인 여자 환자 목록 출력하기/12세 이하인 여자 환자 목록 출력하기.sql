select PT_NAME, PT_NO, GEND_CD, AGE, IFNULL(TLNO, "NONE") as TLNO
from PATIENT
where age <= 12
and gend_cd = 'W'
order by age desc, pt_name