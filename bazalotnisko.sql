DROP SCHEMA IF EXISTS `bazalotniso` ;
CREATE SCHEMA IF NOT EXISTS `bazalotnisko` DEFAULT CHARACTER SET utf8 ;
USE `bazalotnisko` ;

DROP TABLE IF EXISTS `bazalotnisko`.`logi`;
DROP TABLE IF EXISTS `bazalotnisko`.`bilety`;
DROP TABLE IF EXISTS `bazalotnisko`.`pasazerowie`;
DROP TABLE IF EXISTS `bazalotnisko`.`samoloty`;
DROP TABLE IF EXISTS `bazalotnisko`.`pracownicy`;
DROP TABLE IF EXISTS `bazalotnisko`.`miejsca_docelowe`;

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
  #`nr_lotu` VARCHAR(4) NOT NULL,
  `id_samolotu` INT(11) NOT NULL REFERENCES bazalotnisko.samoloty(ID),
  #`id_pasazera` INT(11) NOT NULL REFERENCES bazalotnisko.pasazerowie(ID),
  #`id_pracownika` INT(11) NOT NULL REFERENCES bazalotnisko.pracownicy(ID),
  #`cena` DOUBLE NOT NULL,
  `data_lotu` VARCHAR(10) NOT NULL,
  `data_wystawienia_biletu` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  #`cel` VARCHAR(40) NOT NULL,
  `imie_pasazera` VARCHAR(15) NOT NULL,
  `nazwisko_pasazera` VARCHAR(15) NOT NULL,
  #`model_samolotu` VARCHAR(20) NOT NULL,
  `id_miejsca_docelowego` INT(11) NOT NULL REFERENCES bazalotnisko.miejsca_docelowe(ID),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

/*
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
*/


CREATE TABLE IF NOT EXISTS `bazalotnisko`.`samoloty` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(20) NOT NULL,
  `nr_samolotu` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

insert into samoloty (id, model, nr_samolotu) values (1, 'Boeing 777', 'BH34524');
insert into samoloty (id, model, nr_samolotu) values (2, 'Antonov 225', 'GY76347');
insert into samoloty (id, model, nr_samolotu) values (3, 'Airbus A380', 'TV65842');
insert into samoloty (id, model, nr_samolotu) values (4, 'Concorde', 'NK49852');



CREATE TABLE IF NOT EXISTS `bazalotnisko`.`pracownicy` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `imie` VARCHAR(15) NOT NULL,
  `nazwisko` VARCHAR(15) NOT NULL,
  `haslo` varchar(100) not null,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

insert into pracownicy (id,imie,nazwisko,haslo) 
values (1,'Jan','Kowalski','g3AkO8OQ4/xlCNAUBCyF6w==');
insert into pracownicy (id,imie,nazwisko,haslo) 
values (2,'Adam','Nowak','g3AkO8OQ4/xlCNAUBCyF6w==');
insert into pracownicy (id,imie,nazwisko,haslo) 
values (3,'Anna','Nowakowska','g3AkO8OQ4/xlCNAUBCyF6w==');
insert into pracownicy (id,imie,nazwisko,haslo) 
values (4,'Administrator','','g3AkO8OQ4/xlCNAUBCyF6w==');

/*
insert into samoloty (id,model) 
values (1,'Boeing 777');
insert into samoloty (id,model) 
values (2,'Antonov 225');

insert into pasazerowie (id,imie,nazwisko,data_urodzenia,adres) 
values (1,'John','Smith','1954-12-10','Łódź');
insert into pasazerowie (id,imie,nazwisko,data_urodzenia,adres) 
values (2,'Sarah','Collins','1987-04-18','Warszawa');
*/

insert into bilety (id,/*nr_lotu,id_samolotu,id_pasazera,id_pracownika,cena,*/data_lotu,data_wystawienia_biletu,/*cel,*/imie_pasazera,nazwisko_pasazera/*,model_samolotu*/,id_miejsca_docelowego, id_samolotu)
values (1,/*1111,1,1,1,2000,*/'2016-05-10','2015-12-14 17:04:18',/*,'Moskwa',*/'John','Smith'/*,'Boeing 777'*/,1,1);
insert into bilety (id,/*nr_lotu,id_samolotu,id_pasazera,id_pracownika,cena,*/data_lotu,data_wystawienia_biletu,/*cel,*/imie_pasazera,nazwisko_pasazera/*,model_samolotu*/,id_miejsca_docelowego, id_samolotu)
values (2,/*2222,2,2,2,3000,*/'2016-04-11','2010-01-01 17:04:18',/*'Berlin',*/'Sarah','Collins'/*,'Antonov 225'*/,2,2);



CREATE TABLE IF NOT EXISTS `bazalotnisko`.`miejsca_docelowe` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nazwa` VARCHAR(15) NOT NULL,
  `cena` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

insert into miejsca_docelowe(id,nazwa,cena) values (1,'Moskwa',1000);
insert into miejsca_docelowe(id,nazwa,cena) values (2,'Berlin',2000);
insert into miejsca_docelowe(id,nazwa,cena) values (3,'Madryt',5000);
insert into miejsca_docelowe(id,nazwa,cena) values (4,'Paryz',3000);
insert into miejsca_docelowe(id,nazwa,cena) values (5,'Londyn',1000);
insert into miejsca_docelowe(id,nazwa,cena) values (6,'Rzym',7000);