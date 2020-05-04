-- -----------------------------------------------------
-- Schema task
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `task` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `task` ;

-- -----------------------------------------------------
-- Table `task`.`sensor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `task`.`sensor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sensor_name` VARCHAR(30) NOT NULL,
  `model` VARCHAR(15) NOT NULL,
  `start` INT NOT NULL,
  `end` INT NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  `unit` VARCHAR(20) NOT NULL,
  `location` VARCHAR(40) NULL DEFAULT NULL,
  `sensor_description` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `task`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `task`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` ENUM('ADMIN', 'VIEWER') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `task`.`user` (`username`, `password`, `role`) VALUES ('admin', '$2a$10$LIDPxEWaUMRBHcUkrjWdx.DAXE0EVbmbVrxq782H497KbcVgY5qx.', 'ADMIN');
INSERT INTO `task`.`user` (`username`, `password`, `role`) VALUES ('user', '$2a$10$0iYGLee61cLdrWUhif1yjujsJSIKQ4fR6e1UlyBrKh4klNUKWEuwK', 'VIEWER');

