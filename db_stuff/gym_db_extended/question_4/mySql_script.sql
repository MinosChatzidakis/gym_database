-- SQLINES DEMO *** le SQL Developer Data Modeler 24.3.1.351.0831
-- SQLINES DEMO *** -06-02 20:16:54 EEST
-- SQLINES DEMO *** le Database 11g
-- SQLINES DEMO *** le Database 11g



-- SQLINES DEMO *** no DDL - MDSYS.SDO_GEOMETRY

-- SQLINES DEMO *** no DDL - XMLTYPE

-- SQLINES FOR EVALUATION USE ONLY (14 DAYS)
CREATE TABLE available_rewards 
    ( 
     reward_id    INTEGER  NOT NULL , 
     description  VARCHAR (30) , 
     pts_required INTEGER , 
     valid_for    INTEGER 
    ) 
;

ALTER TABLE available_rewards 
    ADD /* SQLines: Name ignored CONSTRAINT available_rewards_PK */ PRIMARY KEY ( reward_id ) ;

CREATE TABLE Customer 
    ( 
     Customer_ID  INTEGER  NOT NULL , 
     Name         VARCHAR (20)  NOT NULL , 
     Email        VARCHAR (20)  NOT NULL , 
     Phone        VARCHAR (20)  NOT NULL , 
     GYM_Gym_code INTEGER  NOT NULL 
    ) 
;

ALTER TABLE Customer 
    ADD /* SQLines: Name ignored CONSTRAINT Customer_PK */ PRIMARY KEY ( Customer_ID ) ;

CREATE TABLE GYM 
    ( 
     Gym_code INTEGER  NOT NULL , 
     Name     VARCHAR (20)  NOT NULL , 
     address  VARCHAR (20)  NOT NULL , 
     city     VARCHAR (20)  NOT NULL , 
     phone    VARCHAR (20)  NOT NULL , 
     email    VARCHAR (20)  NOT NULL , 
     Services VARCHAR (100)  NOT NULL 
    ) 
;

ALTER TABLE GYM 
    ADD /* SQLines: Name ignored CONSTRAINT GYM_PK */ PRIMARY KEY ( Gym_code ) ;

CREATE TABLE Payment 
    ( 
     Payment_ID                   INTEGER  NOT NULL , 
     Amount                       INTEGER  NOT NULL , 
     Payment_Method               VARCHAR (20)  NOT NULL , 
     Payment_Date                 DATETIME  NOT NULL , 
     Payment_Status               VARCHAR (20)  NOT NULL , 
     Reservation_Reservation_code VARCHAR (20) , 
     pts_transactions_trans_id    INTEGER  NOT NULL 
    ) 
;
CREATE UNIQUE INDEX Payment__IDX ON Payment 
    ( 
     pts_transactions_trans_id ASC 
    ) 
;
CREATE UNIQUE INDEX Payment__IDXv1v1 ON Payment 
    ( 
     Reservation_Reservation_code ASC 
    ) 
;

ALTER TABLE Payment 
    ADD /* SQLines: Name ignored CONSTRAINT Payment_PK */ PRIMARY KEY ( Payment_ID ) ;

CREATE TABLE pts_transactions 
    ( 
     trans_id             INTEGER  NOT NULL , 
     amount               INTEGER , 
     source               VARCHAR (20) , 
     `date`               DATETIME , 
     description          VARCHAR (100) , 
     Customer_Customer_ID INTEGER , 
     Payment_Payment_ID   INTEGER  NOT NULL 
    ) 
;
CREATE UNIQUE INDEX pts_transactions__IDX ON pts_transactions 
    ( 
     Payment_Payment_ID ASC 
    ) 
;

ALTER TABLE pts_transactions 
    ADD /* SQLines: Name ignored CONSTRAINT pts_transactions_PK */ PRIMARY KEY ( trans_id ) ;

CREATE TABLE Reservation 
    ( 
     Reservation_code     VARCHAR (20)  NOT NULL , 
     date_and_time        DATETIME  NOT NULL , 
     invoice_needed       CHAR (1)  NOT NULL , 
     reservation_status   VARCHAR (30)  NOT NULL , 
     Session_Session_Code INTEGER  NOT NULL , 
     Customer_Customer_ID INTEGER 
    ) 
;

ALTER TABLE Reservation 
    ADD /* SQLines: Name ignored CONSTRAINT Reservation_PK */ PRIMARY KEY ( Reservation_code ) ;

CREATE TABLE rewards_distribution 
    ( 
     distr_id                    INTEGER  NOT NULL , 
     available_rewards_reward_id INTEGER , 
     is_used                     CHAR (1) , 
     date_obtained               DATETIME , 
     date_used                   DATETIME , 
     valid_until                 DATETIME , 
     Customer_Customer_ID        INTEGER 
    ) 
;

ALTER TABLE rewards_distribution 
    ADD /* SQLines: Name ignored CONSTRAINT rewards_distribution_PK */ PRIMARY KEY ( distr_id ) ;

CREATE TABLE Services 
    ( 
     Service_Name VARCHAR (20)  NOT NULL , 
     GYM_Gym_code INTEGER  NOT NULL 
    ) 
;

ALTER TABLE Services 
    ADD /* SQLines: Name ignored CONSTRAINT Services_PK */ PRIMARY KEY ( Service_Name, GYM_Gym_code ) ;

CREATE TABLE `Session` 
    ( 
     Session_Code       INTEGER  NOT NULL , 
     Session_Type       VARCHAR (20)  NOT NULL , 
     Description        VARCHAR (100)  NOT NULL , 
     Max_participants   INTEGER  NOT NULL , 
     Time               INTEGER  NOT NULL , 
     Price              VARCHAR (10)  NOT NULL , 
     Availability       CHAR (1)  NOT NULL , 
     Trainer_Trainer_id INTEGER  NOT NULL , 
     GYM_Gym_code       INTEGER  NOT NULL 
    ) 
;

ALTER TABLE `Session` 
    ADD /* SQLines: Name ignored CONSTRAINT Session_PK */ PRIMARY KEY ( Session_Code ) ;

CREATE TABLE Trainer 
    ( 
     Trainer_id   INTEGER  NOT NULL , 
     name         VARCHAR (20)  NOT NULL , 
     Specialty    VARCHAR (20)  NOT NULL , 
     phone        VARCHAR (20)  NOT NULL , 
     email        VARCHAR (20)  NOT NULL , 
     GYM_Gym_code INTEGER  NOT NULL 
    ) 
;

ALTER TABLE Trainer 
    ADD /* SQLines: Name ignored CONSTRAINT Trainer_PK */ PRIMARY KEY ( Trainer_id ) ;

ALTER TABLE Customer 
    ADD CONSTRAINT Customer_GYM_FK FOREIGN KEY 
    ( 
     GYM_Gym_code
    ) 
    REFERENCES GYM 
    ( 
     Gym_code
    ) 
;

ALTER TABLE Payment 
    ADD CONSTRAINT Payment_pts_transactions_FK FOREIGN KEY 
    ( 
     pts_transactions_trans_id
    ) 
    REFERENCES pts_transactions 
    ( 
     trans_id
    ) 
;

ALTER TABLE Payment 
    ADD CONSTRAINT Payment_Reservation_FK FOREIGN KEY 
    ( 
     Reservation_Reservation_code
    ) 
    REFERENCES Reservation 
    ( 
     Reservation_code
    ) 
;

ALTER TABLE pts_transactions 
    ADD CONSTRAINT pts_transactions_Customer_FK FOREIGN KEY 
    ( 
     Customer_Customer_ID
    ) 
    REFERENCES Customer 
    ( 
     Customer_ID
    ) 
;

ALTER TABLE pts_transactions 
    ADD CONSTRAINT pts_transactions_Payment_FK FOREIGN KEY 
    ( 
     Payment_Payment_ID
    ) 
    REFERENCES Payment 
    ( 
     Payment_ID
    ) 
;

ALTER TABLE Reservation 
    ADD CONSTRAINT Reservation_Customer_FK FOREIGN KEY 
    ( 
     Customer_Customer_ID
    ) 
    REFERENCES Customer 
    ( 
     Customer_ID
    ) 
;

ALTER TABLE Reservation 
    ADD CONSTRAINT Reservation_Session_FK FOREIGN KEY 
    ( 
     Session_Session_Code
    ) 
    REFERENCES `Session` 
    ( 
     Session_Code
    ) 
;


ALTER TABLE Services 
    ADD CONSTRAINT Services_GYM_FK FOREIGN KEY 
    ( 
     GYM_Gym_code
    ) 
    REFERENCES GYM 
    ( 
     Gym_code
    ) 
;

ALTER TABLE `Session` 
    ADD CONSTRAINT Session_GYM_FK FOREIGN KEY 
    ( 
     GYM_Gym_code
    ) 
    REFERENCES GYM 
    ( 
     Gym_code
    ) 
;

ALTER TABLE `Session` 
    ADD CONSTRAINT Session_Trainer_FK FOREIGN KEY 
    ( 
     Trainer_Trainer_id
    ) 
    REFERENCES Trainer 
    ( 
     Trainer_id
    ) 
;

ALTER TABLE Trainer 
    ADD CONSTRAINT Trainer_GYM_FK FOREIGN KEY 
    ( 
     GYM_Gym_code
    ) 
    REFERENCES GYM 
    ( 
     Gym_code
    ) 
;



-- SQLINES DEMO *** per Data Modeler Summary Report: 
-- 
-- SQLINES DEMO ***                        10
-- SQLINES DEMO ***                         3
-- SQLINES DEMO ***                        23
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO *** DY                      0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***  TYPE                   0
-- SQLINES DEMO ***  TYPE                   0
-- SQLINES DEMO ***  TYPE BODY              0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO *** EGMENT                  0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO *** ED VIEW                 0
-- SQLINES DEMO *** ED VIEW LOG             0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- 
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- 
-- SQLINES DEMO ***                         0
-- 
-- SQLINES DEMO ***                         0
-- SQLINES DEMO *** A                       0
-- SQLINES DEMO *** T                       0
-- 
-- SQLINES DEMO ***                         2
-- SQLINES DEMO ***                         0