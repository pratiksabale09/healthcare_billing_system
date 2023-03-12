-- ROOM_DETAILS AND ROOM_USAGE
-- ROOM_USAGE_ID
SELECT
    RU.ROOM_ID,
    RU.PATIENT_ID,
    RU.U_DATE,
    RU.DURATION_IN_DAYS,
    RD.ROOM_TYPE,
    RD.ROOM_CHARGE,
    RD.IS_AVAILABLE
FROM
    ROOM_USAGE   R_U
    INNER JOIN ROOM_DETAILS RD
    ON RD.ROOM_ID = RU.ROOM_ID
WHERE
    RU.PATIENT_ID = ?;

-- EQUPMENT_DETAILS AND EQUIPMENT USAGE
SELECT
    EU.EQUIPMENT_USAGE_ID,
    EU.EQUIPMENT_ID,
    EU.PATIENT_ID,
    EU.USAGE_COUNT,
    EU.U_DATE,
    ED.EQUIPMENT_NAME,
    ED.EQUIPMENT_CHARGE_PER_UNIT,
    ED.EQUIPMENT_CATEGORY
FROM
    EQUIPMENT_USAGE   EU
    INNER JOIN EQUIPMENT_DETAILS ED
    ON EU.EQUIPMENT_ID = ED.EQUIPMENT_ID
WHERE
    EU.PATIENT_ID = ?;

-- MEDICINE_USAGE AND MEDICINE_DETAILS
SELECT
    MU.MEDICINE_USAGE_ID, MU.MEDICINE_ID, MU.PATIENT_ID, MU.USAGE_COUNT, MU.U_DATE,
    MD.MEDICINE_NAME, MD.MEDICINE_CHARGE_PER_UNIT, MD.UNITS, MD.BATCH_NO
FROM
    MEDICINE_USAGE   MU
    INNER JOIN MEDICINE_DETAILS MD
    ON MU.MEDICINE_ID = MD.MEDICINE_ID
WHERE
    MU.PATIENT_ID = ?;

-- MEDICAL TEST AND TEST CONDUCTED

SELECT
    MT.MEDICAL_TEST_ID, MT.TEST_NAME, MT.TEST_CHARGE,
    MTC.TEST_CONDUCTED_ID,MTC.PATIENT_ID, MTC.U_DATE
FROM
    MEDICAL_TESTS MT
    INNER JOIN MEDICAL_TEST_CONDUCTED MTC
    ON MT.MEDICAL_TEST_ID = MTC.MEDICAL_TEST_ID
WHERE
    MTC.PATIENT_ID = ?;


    
