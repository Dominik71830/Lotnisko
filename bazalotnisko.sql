DROP SCHEMA IF EXISTS `bazalotniso` ;
CREATE SCHEMA IF NOT EXISTS `bazalotnisko` DEFAULT CHARACTER SET utf8 ;
USE `tempbaza` ;

DROP TABLE IF EXISTS `bazalotnisko`.`logi`;
DROP TABLE IF EXISTS `bazalotnisko`.`bilety`;
DROP TABLE IF EXISTS `bazalotnisko`.`pasazerowie`;
DROP TABLE IF EXISTS `bazalotnisko`.`samoloty`;
DROP TABLE IF EXISTS `bazalotnisko`.`pracownicy`;

/*CREATE TABLE IF NOT EXISTS `bazalotnisko`.`logi` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_obiektu` INT(11) NOT NULL references tempbaza.uchodzcy(id),
  `data_powstania` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `akcja` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;*/

CREATE TABLE IF NOT EXISTS `bazalotnisko`.`bilety` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nr_lotu` VARCHAR(4) NOT NULL,
  `id_maszyny` INT(11) NOT NULL REFERENCES bazalotnisko.samoloty(ID),
  `id_pasazera` INT(11) NOT NULL REFERENCES bazalotnisko.pasazerowie(ID),
  `cena` DOUBLE NOT NULL,
  `data_lotu` VARCHAR(30) NOT NULL,
  `data_wystawienia_biletu` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `bazalotnisko`.`pasazerowie` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `imie` VARCHAR(15) NOT NULL,
  `nazwisko` VARCHAR(15) NOT NULL,
  `data_urodzenia` VARCHAR(10) NOT NULL,
  `adres` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `bazalotnisko`.`samoloty` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `bazalotnisko`.`pracownicy` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `imie` VARCHAR(15) NOT NULL,
  `nazwisko` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;