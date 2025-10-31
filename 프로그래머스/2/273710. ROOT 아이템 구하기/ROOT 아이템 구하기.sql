select a.ITEM_ID, ITEM_NAME
from ITEM_INFO a, ITEM_TREE b
where a.ITEM_ID = b.ITEM_ID
and PARENT_ITEM_ID is null
order by a.ITEM_ID