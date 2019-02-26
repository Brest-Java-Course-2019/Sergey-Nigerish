-- -----------------------------------------------------
-- Schema HR
-- -----------------------------------------------------
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS tariff;

-- -----------------------------------------------------
-- Table tariff
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tariff (
  tariffsId SERIAL NOT NULL,
  tariffName VARCHAR(45) NOT NULL UNIQUE,
  tariffDeleted BOOLEAN NULL DEFAULT '0',
  PRIMARY KEY (tariffsId)
);

-- -----------------------------------------------------
-- Table Client
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS clients (
  clientContractId SERIAL NOT NULL,
  clientContractDay_date DATE NOT NULL,
  clientFIO VARCHAR(140) NOT NULL,
  clientAddress VARCHAR(90) NOT NULL,
  clientBlocked BOOLEAN NULL,
  client_to_idTariffs INTEGER NOT NULL,
  clientDeleted BOOLEAN NULL DEFAULT '0',
  PRIMARY KEY (clientContractId),
  CONSTRAINT clients_to_tariffs_fk
    FOREIGN KEY (client_to_idTariffs)
    REFERENCES tariff (tariffsId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);