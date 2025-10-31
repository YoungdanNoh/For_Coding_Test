select CAR_ID,
        CASE
            WHEN CAR_ID IN (select CAR_ID
                            from CAR_RENTAL_COMPANY_RENTAL_HISTORY
                            where '2022-10-16' BETWEEN START_DATE AND END_DATE) THEN '대여중'
            ELSE '대여 가능'
        END AS AVAILABILITY
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by CAR_ID
order by CAR_ID desc