-- -----------------------------------------------------
-- Table tariff
-- -----------------------------------------------------
INSERT INTO tariffs (tariffName) VALUES ('Talkative');
INSERT INTO tariffs (tariffName) VALUES ('Silent');
INSERT INTO tariffs (tariffName) VALUES ('Corporate');
INSERT INTO tariffs (tariffName) VALUES ('Family');
INSERT INTO tariffs (tariffName, tariffDeleted) VALUES ('International', true);

-- -----------------------------------------------------
-- Table Clients
-- -----------------------------------------------------
INSERT INTO clients (clientContractDay_date
                    ,clientFIO
                    ,clientAddress
                    ,clientBlocked
                    ,client_to_idTariff)
            VALUES ('2015-10-02'
                    ,'Sergey Pupkin'
                    ,'Lesnaya st.'
                    ,0
                    ,1);

INSERT INTO clients (clientContractDay_date
                    ,clientFIO
                    ,clientAddress
                    ,clientBlocked
                    ,client_to_idTariff)
            VALUES ('2016-03-16'
                    ,'Dmitry Ivanov'
                    ,'Vishevaya st.'
                    ,1
                    ,2);

INSERT INTO clients (clientContractDay_date
                    ,clientFIO
                    ,clientAddress
                    ,clientBlocked
                    ,client_to_idTariff
                    ,clientDeleted)
            VALUES ('2016-03-17'
                    ,'Demeduk Iosif'
                    ,'Proletarskaya st.'
                    ,1
                    ,3
                    ,true);

INSERT INTO clients (clientContractDay_date
                    ,clientFIO
                    ,clientAddress
                    ,clientBlocked
                    ,client_to_idTariff)
            VALUES ('2016-05-19'
                    ,'Hand made shop'
                    ,'Industrial st.'
                    ,0
                    ,3);

INSERT INTO clients (clientContractDay_date
                    ,clientFIO
                    ,clientAddress
                    ,clientBlocked
                    ,client_to_idTariff)
            VALUES ('2017-07-29'
                    ,'Bogdan Belousov'
                    ,'Budenogo st.'
                    ,0
                    ,1);

INSERT INTO clients (clientContractDay_date
                    ,clientFIO
                    ,clientAddress
                    ,clientBlocked
                    ,client_to_idTariff)
            VALUES ('2018-01-02'
                    ,'Rozaliya Tixonenko'
                    ,'Basovskaya st.'
                    ,0
                    ,4);

INSERT INTO clients (clientContractDay_date
                    ,clientFIO
                    ,clientAddress
                    ,clientBlocked
                    ,client_to_idTariff)
            VALUES ('2018-01-02'
                    ,'Vadim Krakov'
                    ,'Borovaya st.'
                    ,0
                    ,1);

INSERT INTO clients (clientContractDay_date
                    ,clientFIO
                    ,clientAddress
                    ,clientBlocked
                    ,client_to_idTariff)
            VALUES ('2018-07-02'
                    ,'Marta Pamfilova'
                    ,'Basmanova st.'
                    ,0
                    ,2);

INSERT INTO clients (clientContractDay_date
                    ,clientFIO
                    ,clientAddress
                    ,clientBlocked
                    ,client_to_idTariff)
            VALUES ('2019-02-19'
                    ,'Oksana Doronina'
                    ,'Kovrova st.'
                    ,0
                    ,1);