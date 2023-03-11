-- ROOM_DETAILS AND ROOM_USAGE
-- ROOM_USAGE_ID
SELECT
    RU.ROOM_ID, RU.PATIENT_ID, RU.U_DATE, RU.DURATION_IN_DAYS,
    RD.ROOM_TYPE, RD.ROOM_CHARGE, RD.IS_AVAILABLE
FROM
    ROOM_USAGE   R_U
    INNER JOIN ROOM_DETAILS RD
    ON RD.ROOM_ID = RU.ROOM_ID
WHERE
    RU.PATIENT_ID = ?;