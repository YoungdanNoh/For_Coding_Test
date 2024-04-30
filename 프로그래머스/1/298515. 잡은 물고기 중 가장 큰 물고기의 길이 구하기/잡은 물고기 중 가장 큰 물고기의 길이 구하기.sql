select concat(length,"cm") as MAX_LENGTH
from FISH_INFO
order by length desc
limit 1