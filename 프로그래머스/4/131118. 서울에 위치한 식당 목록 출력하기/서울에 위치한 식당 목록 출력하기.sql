select a.REST_ID, REST_NAME, FOOD_TYPE, FAVORITES, ADDRESS, round(avg(REVIEW_SCORE), 2) as SCORE
from REST_INFO as a, REST_REVIEW as b
where a.REST_ID = b.REST_ID
and address like "서울%"
group by a.rest_id, REST_NAME, FOOD_TYPE, FAVORITES, ADDRESS
order by SCORE desc, FAVORITES desc