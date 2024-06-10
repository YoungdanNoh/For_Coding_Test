select A.id, A.genotype, B.GENOTYPE as PARENT_GENOTYPE
from ECOLI_DATA as A, ECOLI_DATA as B 
where A.parent_id = B.ID
and A.GENOTYPE & B.GENOTYPE = B.GENOTYPE
order by id