-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema 
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `dbShooterApp` ;

-- -----------------------------------------------------
-- Schema 
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbShooterApp` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema dbShooterApp
-- -----------------------------------------------------
USE `dbShooterApp` ;

-- -----------------------------------------------------
-- Table `player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `player` ;

CREATE TABLE IF NOT EXISTS `player` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `nick` VARCHAR(45) NOT NULL,
  `birth_date` DATE NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nick_UNIQUE` (`nick` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));


-- -----------------------------------------------------
-- Table `duel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `duel` ;

CREATE TABLE IF NOT EXISTS `duel` (
  `id` INT NOT NULL,
  `id_player_one` INT NOT NULL,
  `id_player_two` INT NOT NULL,
  `start_duel` DATETIME NOT NULL,
  `end_duel` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_duel_player_two_idx` (`id_player_two` ASC),
  CONSTRAINT `fk_duel_player_two`
    FOREIGN KEY (`id_player_two`)
    REFERENCES `player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_duel_player_one`
    FOREIGN KEY (`id_player_one`)
    REFERENCES `player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'mantem informacao sobre o duelo';


-- -----------------------------------------------------
-- Table `body_part`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `body_part` ;

CREATE TABLE IF NOT EXISTS `body_part` (
  `id` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `body_part_hit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `body_part_hit` ;

CREATE TABLE IF NOT EXISTS `body_part_hit` (
  `id` INT NOT NULL,
  `id_body_part` INT NOT NULL,
  `perc_damage_hit` DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_body_part_hit_1_idx` (`id_body_part` ASC),
  CONSTRAINT `fk_body_part_hit_1`
    FOREIGN KEY (`id_body_part`)
    REFERENCES `body_part` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `turn_duel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `turn_duel` ;

CREATE TABLE IF NOT EXISTS `turn_duel` (
  `id` INT NOT NULL,
  `id_duel` INT NOT NULL,
  `turn` INT NOT NULL,
  `id_player_turn` INT NOT NULL,
  `id_body_part_attack` INT NOT NULL,
  `id_body_part_defense` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_turn_duel_1_idx` (`id_duel` ASC),
  INDEX `fk_turn_body_attack_idx` (`id_body_part_attack` ASC),
  INDEX `fk_turn_body_defense_idx` (`id_body_part_defense` ASC),
  CONSTRAINT `fk_turn_duel_1`
    FOREIGN KEY (`id_duel`)
    REFERENCES `duel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_turn_body_attack`
    FOREIGN KEY (`id_body_part_attack`)
    REFERENCES `body_part` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_turn_body_defense`
    FOREIGN KEY (`id_body_part_defense`)
    REFERENCES `body_part` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `turn_result`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `turn_result` ;

CREATE TABLE IF NOT EXISTS `turn_result` (
  `id` INT NOT NULL,
  `id_duel` INT NOT NULL,
  `id_player_one_demage_receive` INT NOT NULL,
  `id_player_two_demage_receive` INT NOT NULL,
  `turn` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_turn_result_duel_idx` (`id_duel` ASC),
  CONSTRAINT `fk_turn_result_duel`
    FOREIGN KEY (`id_duel`)
    REFERENCES `duel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `duel_result`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `duel_result` ;

CREATE TABLE IF NOT EXISTS `duel_result` (
  `id` INT NOT NULL,
  `id_duel` INT NOT NULL,
  `id_player` INT NOT NULL,
  `type` VARCHAR(3) NOT NULL COMMENT 'WIN - ganhou\nLOS - PERDEU\nDBL - empate (Doubleck)',
  PRIMARY KEY (`id`),
  INDEX `fk_duel_result_idx` (`id_duel` ASC),
  CONSTRAINT `fk_duel_result`
    FOREIGN KEY (`id_duel`)
    REFERENCES `duel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Possui o duelo e o ganhador do duelo com base no calculo da tabela turn_result onde o ganhador causou 100% de dano primeiro';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
