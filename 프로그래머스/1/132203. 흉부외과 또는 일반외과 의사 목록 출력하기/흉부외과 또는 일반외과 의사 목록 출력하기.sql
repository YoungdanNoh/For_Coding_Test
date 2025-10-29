select dr_name, dr_id, mcdp_cd, DATE_FORMAT(hire_ymd, '%Y-%m-%d') AS hire_date
from doctor
where mcdp_cd = "CS" or mcdp_cd = "GS"
order by hire_date desc, dr_name asc