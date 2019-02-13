-- -----------------------------------------------------
-- Schema HR
-- -----------------------------------------------------
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS tariffs;

-- -----------------------------------------------------
-- Table tariffs
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tariffs (
  tariffsId SERIAL NOT NULL,
  tariffName VARCHAR(45) NOT NULL UNIQUE,
  tariffDeleted BOOLEAN NULL DEFAULT '0',
  PRIMARY KEY (tariffsId)
);

-- -----------------------------------------------------
-- Table Clients
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS clients (
  clientContractId SERIAL NOT NULL,
  clientContractDay_date DATE NOT NULL,
  clientFIO VARCHAR(140) NOT NULL,
  clientAddress VARCHAR(90) NOT NULL,
  clientBlocked BOOLEAN NULL,
  clientDeleted BOOLEAN NULL DEFAULT '0',
  client_to_idTariffs INTEGER NOT NULL,
  PRIMARY KEY (clientContractId),
  CONSTRAINT clients_to_tariffs_fk
    FOREIGN KEY (client_to_idTariffs)
    REFERENCES tariffs (tariffsId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);