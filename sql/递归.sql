
area_code: 子ID
parent_code : 父ID

where s1.area_code = 110101000000  查询父和子
where s1.parent_code = 110101000000  查询子及子子孙孙


WITH RECURSIVE r_t AS (
    SELECT s1.*
    FROM cnarea_2020 s1
    WHERE s1.area_code = 110101000000
    UNION ALL
    SELECT s2.*
    FROM cnarea_2020 s2
             INNER JOIN r_t ON r_t.area_code = s2.parent_code
)
SELECT *
FROM r_t;
