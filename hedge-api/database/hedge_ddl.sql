drop database if exists hedge;
create database hedge;
USE `hedge` ;

CREATE TABLE IF NOT EXISTS `hedge`.`company_type` (
  `company_type_id` INT NOT NULL AUTO_INCREMENT,
  `company_type_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`company_type_id`),
  UNIQUE INDEX `company_type_name_UNIQUE` (`company_type_name` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS `hedge`.`company` (
  `company_id` INT NOT NULL AUTO_INCREMENT,
  `company_type_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `stock_price` INT NOT NULL,
  `risk` VARCHAR(45) NOT NULL,
  `stock_total` INT NOT NULL,
  `dividend` INT NOT NULL,
  PRIMARY KEY (`company_id`),
  INDEX `fk_company_type_id_idx` (`company_type_id` ASC) VISIBLE,
    FOREIGN KEY (`company_type_id`)
    REFERENCES `hedge`.`company_type` (`company_type_id`));

CREATE TABLE IF NOT EXISTS `hedge`.`leaderboard` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `score` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `hedge`.`game` (
  `game_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL UNIQUE,
  `year_number` INT NOT NULL,
  PRIMARY KEY (`game_id`));

CREATE TABLE IF NOT EXISTS `hedge`.`market` (
  `company_id` INT NOT NULL,
  `price` INT NOT NULL,
  `year_num` INT NOT NULL,
  `market_id` INT NOT NULL AUTO_INCREMENT,
  `game_id` INT NOT NULL,
  `stock_purchased` INT NOT NULL,
  `long` TINYINT NOT NULL,
  `is_bankrupt` TINYINT NOT NULL,
  INDEX `fk_company_id_idx` (`company_id` ASC) VISIBLE,
  PRIMARY KEY (`market_id`),
  INDEX `fk_game_id_idx` (`game_id` ASC) VISIBLE,
    FOREIGN KEY (`company_id`)
    REFERENCES `hedge`.`company` (`company_id`),
    FOREIGN KEY (`game_id`)
    REFERENCES `hedge`.`game` (`game_id`));

CREATE TABLE IF NOT EXISTS `hedge`.`market_type` (
  `market_id` INT NOT NULL AUTO_INCREMENT,
  `roll` INT NOT NULL,
  `company_id` INT NOT NULL,
  `bull_modify` INT NOT NULL,
  `bear_modify` INT NOT NULL,
  PRIMARY KEY (`market_id`),
  INDEX `fk_company_id_idx` (`company_id` ASC) VISIBLE,
    FOREIGN KEY (`company_id`)
    REFERENCES `hedge`.`company` (`company_id`));
    

