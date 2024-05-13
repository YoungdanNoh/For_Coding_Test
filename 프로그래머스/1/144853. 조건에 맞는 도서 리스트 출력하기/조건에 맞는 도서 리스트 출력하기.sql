SELECT BOOK_ID, date_format(PUBLISHED_DATE, "%Y-%m-%d") as PUBLISHED_DATE
from BOOK
where category = '인문'
and YEAR(PUBLISHED_DATE) = 2021
order by PUBLISHED_DATE