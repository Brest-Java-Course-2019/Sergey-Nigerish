-- -----------------------------------------------------
-- Schema HR
-- -----------------------------------------------------
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS tariffs;

-- -----------------------------------------------------
-- Table tariff
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tariffs (
  tariffId INT NOT NULL AUTO_INCREMENT,
  tariffName VARCHAR(45) NOT NULL,
  tariffDeleted TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (tariffId)
);

-- -----------------------------------------------------
-- Table Clients
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS clients (
  clientContractId INT NOT NULL AUTO_INCREMENT,
  clientContractDay_date DATE NOT NULL,
  clientFIO VARCHAR(140) NOT NULL,
  clientAddress VARCHAR(90) NOT NULL,
  clientBlocked TINYINT(1) NULL DEFAULT 0,
  client_to_idTariff INTEGER NOT NULL,
  clientDeleted TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (clientContractId),
  CONSTRAINT clients_to_tariffs_fk
    FOREIGN KEY (client_to_idTariff)
    REFERENCES tariffs (tariffId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);