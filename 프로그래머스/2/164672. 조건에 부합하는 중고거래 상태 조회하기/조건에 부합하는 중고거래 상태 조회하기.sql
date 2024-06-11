SELECT BOARD_ID, WRITER_ID, TITLE, PRICE, (CASE
                                               WHEN A.STATUS = 'SALE' THEN '판매중'
                                               WHEN A.STATUS = 'RESERVED' THEN '예약중'
                                               WHEN A.STATUS = 'DONE' THEN '거래완료'
                                               END) AS 'STATUS'
FROM USED_GOODS_BOARD AS A
WHERE CREATED_DATE LIKE '2022-10-05'
ORDER BY BOARD_ID DESC