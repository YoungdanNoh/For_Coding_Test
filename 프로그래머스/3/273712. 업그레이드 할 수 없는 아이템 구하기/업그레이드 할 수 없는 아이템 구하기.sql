select a.ITEM_ID, ITEM_NAME, RARITY
from ITEM_INFO a, ITEM_TREE b
where a.ITEM_ID = b.ITEM_ID
and a.ITEM_ID not IN (select PARENT_ITEM_ID
                      from ITEM_TREE
                      where PARENT_ITEM_ID is not null)
order by a.ITEM_ID desc