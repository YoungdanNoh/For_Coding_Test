SELECT 
       CASE
            WHEN (PRICE div 10000) = 0 THEN '0'
            ELSE CONCAT((PRICE div 10000), '0000')
       END AS PRICE_GROUP,
       COUNT(*) AS "PRODUCTS"
FROM PRODUCT
GROUP BY PRICE_GROUP
ORDER BY PRICE_GROUP