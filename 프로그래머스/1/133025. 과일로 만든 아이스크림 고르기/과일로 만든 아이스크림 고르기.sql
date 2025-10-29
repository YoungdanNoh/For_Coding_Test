select a.FLAVOR
from FIRST_HALF as a, ICECREAM_INFO as b
where a.FLAVOR = b.FLAVOR
and a.TOTAL_ORDER > 3000
and b.INGREDIENT_TYPE = 'fruit_based'
order by a.TOTAL_ORDER desc