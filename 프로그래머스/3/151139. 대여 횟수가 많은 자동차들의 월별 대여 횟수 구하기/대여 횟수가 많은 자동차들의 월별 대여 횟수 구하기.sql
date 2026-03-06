select DATE_FORMAT(START_DATE, "%m") as MONTH, CAR_ID, count(*) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where (CAR_ID) IN (select CAR_ID
                   from CAR_RENTAL_COMPANY_RENTAL_HISTORY
                   where DATE_FORMAT(START_DATE, "%Y-%m") >= "2022-08"
                   and DATE_FORMAT(START_DATE, "%Y-%m") <= "2022-10"
                   group by CAR_ID
                   having count(*) >= 5)
and DATE_FORMAT(START_DATE, "%Y-%m") >= "2022-08"
and DATE_FORMAT(START_DATE, "%Y-%m") <= "2022-10"
group by MONTH, CAR_ID
order by MONTH, CAR_ID desc