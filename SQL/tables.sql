-- 1) INSURANCE ANT COVER TYPE *
CREATE TABLE INSURANCE_COMPANY (
    INSURANCE_COMPANY_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    COMPANY_NAME VARCHAR2(50),
    ADDRESS VARCHAR2(200),
    PHONE_NUMBERS VARCHAR2(15),
    EMAIL_ADDRESS VARCHAR2(60),
    TAX_ID_NUMBER VARCHAR2(20)
);

--2)INSURANCE_COVER_TYPE *
CREATE TABLE INSURANCE_COVER_TYPE (
    INSURANCE_COVER_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    INSURANCE_COVER_TYPE VARCHAR2(50),
    INSURANCE_COMPANY_ID INT NOT NULL,
    PREMIUM_AMOUNT DECIMAL(10, 2),
    CONSTRAINT FK_INSURANCE_COMPANY_ID FOREIGN KEY (INSURANCE_COMPANY_ID) REFERENCES INSURANCE_COMPANY(INSURANCE_COMPANY_ID)

);

-- 3) PATIENT TABLE *
CREATE TABLE PATIENT (
    PATIENT_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    FIRST_NAME VARCHAR2(30),
    LAST_NAME VARCHAR2(30),
    DATE_OF_BIRTH DATE,
    ADDRESS VARCHAR2(200),
    PHONE_NUMBER VARCHAR2(15),
    GENDER VARCHAR2(10),
    AGE INT,
    EMAIL_ADDRESS VARCHAR2(60),
    IS_INSURED NUMBER(1),
    INSURANCE_COVER_ID INT,
    CONSTRAINT FK_INSURANCE_COVER_ID FOREIGN KEY (INSURANCE_COVER_ID) REFERENCES INSURANCE_COVER_TYPE(INSURANCE_COVER_ID)
);

-- 4) SERVICES OR TREATMENT CHARGE *
CREATE TABLE TREATMENT_SERVICES_DETAILS (
    TREATMENT_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    TREATMENT_NAME VARCHAR(100),
    TREATMENT_CHARGE DECIMAL(10, 2)
);

--5) ROOM details *
CREATE TABLE ROOM_DETAILS (
    ROOM_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    ROOM_TYPE VARCHAR(50),
    IS_AVAILABLE NUMBER(1) DEFAULT 1,
    ROOM_CHARGE DECIMAL(10, 2)
);

--6) doctor details *
CREATE TABLE DOCTOR_DETAILS (
    DOCTOR_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    DOCTOR_NAME VARCHAR(100),
    QUALIFICATION VARCHAR(100),
    SPECIALTY VARCHAR(60),
    CONSULTATION_CHARGE DECIMAL(10, 2)
);

--7) medicine details *
CREATE TABLE MEDICINE_DETAILS (
    MEDICINE_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    MEDICINE_NAME VARCHAR(255),
    MEDICINE_CHARGE_PER_UNIT DECIMAL(10, 2),
    UNITS INT,
    BATCH_NO VARCHAR(50)
);

--8) equipment_details *
CREATE TABLE EQUIPMENT_DETAILS (
    EQUIPMENT_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    EQUIPMENT_NAME VARCHAR(255),
    EQUIPMENT_CHARGE_PER_UNIT INT,
    EQUIPMENT_CATEGORY VARCHAR(255),
    UNITS_AVAILABLE INT
);

--9) medical test *
CREATE TABLE MEDICAL_TESTS (
    MEDICAL_TEST_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    TEST_NAME VARCHAR(100),
    TEST_CHARGE DECIMAL(10, 2)
);


--10) equipment usage *
CREATE TABLE EQUIPMENT_USAGE (
    EQUIPMENT_USAGE_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    EQUIPMENT_ID INT NOT NULL,
    PATIENT_ID INT NOT NULL,
    USAGE_COUNT INT,
    U_DATE DATE,
    CONSTRAINT FK_EQUIPMENT_ID FOREIGN KEY (EQUIPMENT_ID) REFERENCES EQUIPMENT_DETAILS(EQUIPMENT_ID),
    CONSTRAINT FK_PATIENT_ID FOREIGN KEY (PATIENT_ID) REFERENCES PATIENT(PATIENT_ID)
);

--11) medicine usage *
CREATE TABLE MEDICINE_USAGE (
    MEDICINE_USAGE_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    MEDICINE_ID INT NOT NULL,
    PATIENT_ID INT NOT NULL,
    USAGE_COUNT INT NOT NULL,
    U_DATE DATE,
    CONSTRAINT FK_MEDICINE_ID FOREIGN KEY (MEDICINE_ID) REFERENCES MEDICINE_DETAILS(MEDICINE_ID),
    CONSTRAINT FK_ME_PATIENT_ID FOREIGN KEY (PATIENT_ID) REFERENCES PATIENT(PATIENT_ID)
);


-- 12) room usage *
CREATE TABLE ROOM_USAGE (
    ROOM_USAGE_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    ROOM_ID INT NOT NULL,
    PATIENT_ID INT NOT NULL,
    DURATION_IN_DAYS INT,
    U_DATE DATE,
    CONSTRAINT FK_ROOM_ID FOREIGN KEY (ROOM_ID) REFERENCES ROOM_DETAILS(ROOM_ID),
    CONSTRAINT FK_RU_PATIENT_ID FOREIGN KEY (PATIENT_ID) REFERENCES PATIENT(PATIENT_ID)
);

-- 13) medical test conducted *
CREATE TABLE MEDICAL_TEST_CONDUCTED (
    TEST_CONDUCTED_ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    MEDICAL_TEST_ID INT NOT NULL,
    PATIENT_ID INT NOT NULL,
    U_DATE DATE,
    CONSTRAINT FK_MEDICAL_TEST_ID FOREIGN KEY (MEDICAL_TEST_ID)REFERENCES MEDICAL_TESTS(MEDICAL_TEST_ID),
    CONSTRAINT FK_MTC_PATIENT_ID FOREIGN KEY (PATIENT_ID) REFERENCES PATIENT(PATIENT_ID)
);

-- 14) treatment_services used *
CREATE TABLE TREATMENT_SERVICES_USAGE (
    TREATMENT_USAGE_ID INT GENERATED BY DEFAULT AS IDENTITY,
    TREATMENT_ID INT NOT NULL,
    PATIENT_ID INT NOT NULL,
    DOCTOR_ID INT NOT NULL,
    U_DATE DATE,
    CONSTRAINT FK_TREATMENT_ID FOREIGN KEY (TREATMENT_ID) REFERENCES TREATMENT_SERVICES_DETAILS(TREATMENT_ID),
    CONSTRAINT FK_TSU_PATIENT_ID FOREIGN KEY (PATIENT_ID) REFERENCES PATIENT(PATIENT_ID),
    CONSTRAINT FK_TSU_DOCTOR_ID FOREIGN KEY (DOCTOR_ID) REFERENCES DOCTOR_DETAILS(DOCTOR_ID)
);

-- 15) bill SEGMENT *
CREATE TABLE BILL_SEGMENT(
    PATIENT_ID INT,
    SEGMENT_NAME INT,
    SEGMENT_BILL_AMOUNT DECIMAL(10, 2),
    STATUS NUMBER(1),
    BILL_DATE DATE,
    CONSTRAINT FK_BS_PATIENT_ID FOREIGN KEY (PATIENT_ID) REFERENCES PATIENT(PATIENT_ID)
);