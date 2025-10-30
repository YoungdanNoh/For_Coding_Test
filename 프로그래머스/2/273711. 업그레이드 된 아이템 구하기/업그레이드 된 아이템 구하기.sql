select ITEM_ID, ITEM_NAME, RARITY
from ITEM_INFO
where ITEM_ID IN (select b.ITEM_ID
                 from ITEM_INFO a, ITEM_TREE b
                 where a.ITEM_ID = b.PARENT_ITEM_ID
                 and RARITY = 'RARE')
order by ITEM_ID desc