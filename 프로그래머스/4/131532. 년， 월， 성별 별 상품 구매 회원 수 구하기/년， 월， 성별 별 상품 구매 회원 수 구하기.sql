-- 코드를 입력하세요
SELECT YEAR(SALES_DATE) as YEAR, MONTH(SALES_DATE) as MONTH, GENDER, count(distinct a.USER_ID) as USERS
from USER_INFO a, ONLINE_SALE b
where a.USER_ID = b.USER_ID
and GENDER is not null
group by YEAR, MONTH, GENDER
order by YEAR, MONTH, GENDER