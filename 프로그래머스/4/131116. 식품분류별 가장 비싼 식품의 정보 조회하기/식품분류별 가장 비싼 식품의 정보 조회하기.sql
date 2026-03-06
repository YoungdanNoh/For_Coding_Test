SELECT CATEGORY, PRICE as MAX_PRICE, PRODUCT_NAME
from FOOD_PRODUCT
where (CATEGORY, PRICE) IN (select CATEGORY, max(PRICE)
                            from FOOD_PRODUCT
                            where CATEGORY IN ('과자', '국', '김치', '식용유')
                            group by CATEGORY)
order by MAX_PRICE desc