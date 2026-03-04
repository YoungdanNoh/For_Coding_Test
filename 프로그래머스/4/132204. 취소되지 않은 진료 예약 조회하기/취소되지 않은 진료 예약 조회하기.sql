with register_patient as(
    select *
    from APPOINTMENT
    where APNT_CNCL_YN = 'N'
    and DATE_FORMAT(APNT_YMD, '%Y-%m-%d') = '2022-04-13'
    and MCDP_CD = 'CS'
)
SELECT rp.APNT_NO, p.PT_NAME, p.PT_NO, d.MCDP_CD, d.DR_NAME, rp.APNT_YMD
from PATIENT as p, DOCTOR as d, register_patient as rp
where p.PT_NO = rp.PT_NO
and d.DR_ID = rp.MDDR_ID
order by APNT_YMD;