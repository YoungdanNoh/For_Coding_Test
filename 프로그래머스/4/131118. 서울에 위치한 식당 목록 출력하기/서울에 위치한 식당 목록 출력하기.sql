with seoul_rest as(
    select *
    from REST_INFO
    where ADDRESS like "서울%"
)
select sr.REST_ID, REST_NAME, FOOD_TYPE, FAVORITES, ADDRESS, round(avg(REVIEW_SCORE), 2) as SCORE
from seoul_rest sr, REST_REVIEW as rr
where sr.REST_ID = rr.REST_ID
group by sr.REST_ID, REST_NAME, FOOD_TYPE, FAVORITES, ADDRESS
order by score desc, FAVORITES desc