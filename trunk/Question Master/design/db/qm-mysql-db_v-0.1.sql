SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `question_master` ;
CREATE SCHEMA IF NOT EXISTS `question_master` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `question_master` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` BIGINT NOT NULL,
  `user_name` VARCHAR(100) NOT NULL,
  `password` VARCHAR(25) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(100) NULL,
  `middle_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `uk_email_id` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mstr_access_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mstr_access_types` ;

CREATE TABLE IF NOT EXISTS `mstr_access_types` (
  `type` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`type`),
  UNIQUE INDEX `uk_type` (`type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `question_bank`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `question_bank` ;

CREATE TABLE IF NOT EXISTS `question_bank` (
  `bank_id` BIGINT NOT NULL,
  `bank_name` VARCHAR(228) NOT NULL,
  `created_date` TIMESTAMP NOT NULL,
  `updated_date` TIMESTAMP NULL,
  `user_id` BIGINT NOT NULL,
  `description` VARCHAR(500) NULL,
  `access_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`bank_id`),
  INDEX `fk_question_bank_user_id` (`user_id` ASC),
  UNIQUE INDEX `uk_user_qbank` (`bank_name` ASC, `user_id` ASC),
  INDEX `fk_question_bank_mstr_access_types1_idx` (`access_type` ASC),
  CONSTRAINT `fk_question_bank_qm_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_bank_mstr_access_types1`
    FOREIGN KEY (`access_type`)
    REFERENCES `mstr_access_types` (`type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test_paper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_paper` ;

CREATE TABLE IF NOT EXISTS `test_paper` (
  `test_paper_id` BIGINT NOT NULL,
  `test_paper_name` VARCHAR(228) NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`test_paper_id`),
  UNIQUE INDEX `test_paper_name_UNIQUE` (`test_paper_name` ASC),
  INDEX `fk_test_paper_qm_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_test_paper_qm_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test_section`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_section` ;

CREATE TABLE IF NOT EXISTS `test_section` (
  `section_id` BIGINT NOT NULL,
  `section_name` VARCHAR(228) NOT NULL,
  `test_paper_id` BIGINT NOT NULL,
  `display_index` INT NULL,
  PRIMARY KEY (`section_id`),
  INDEX `fk_test_section_test_paper1_idx` (`test_paper_id` ASC),
  CONSTRAINT `fk_test_section_test_paper1`
    FOREIGN KEY (`test_paper_id`)
    REFERENCES `test_paper` (`test_paper_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test_session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_session` ;

CREATE TABLE IF NOT EXISTS `test_session` (
  `session_id` BIGINT NOT NULL,
  `start_time` TIMESTAMP NULL,
  `duration` FLOAT NULL,
  `end_time` TIMESTAMP NULL,
  `user_id` BIGINT NOT NULL,
  `test_paper_id` BIGINT NOT NULL,
  `randomize` TINYINT(1) NULL DEFAULT FALSE,
  `relaxed` TINYINT(1) NULL DEFAULT TRUE,
  PRIMARY KEY (`session_id`),
  INDEX `fk_test_session_qm_user1_idx` (`user_id` ASC),
  INDEX `fk_test_session_test_paper1_idx` (`test_paper_id` ASC),
  CONSTRAINT `fk_test_session_qm_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_test_session_test_paper1`
    FOREIGN KEY (`test_paper_id`)
    REFERENCES `test_paper` (`test_paper_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `role` ;

CREATE TABLE IF NOT EXISTS `role` (
  `role_id` BIGINT NOT NULL,
  `role_title` VARCHAR(120) NULL,
  `role_description` VARCHAR(400) NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_role_map`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_role_map` ;

CREATE TABLE IF NOT EXISTS `user_role_map` (
  `user_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  INDEX `fk_qm_user_role_map_qm_user1_idx` (`user_id` ASC),
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_qm_user_role_map_qm_role1_idx` (`role_id` ASC),
  CONSTRAINT `fk_qm_user_role_map_qm_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_qm_user_role_map_qm_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_session` ;

CREATE TABLE IF NOT EXISTS `user_session` (
  `session_id` VARCHAR(228) NOT NULL,
  `user_id` BIGINT NOT NULL,
  `login_time` TIMESTAMP NOT NULL,
  `logout_time` TIMESTAMP NULL,
  `is_active` TINYINT(1) NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`session_id`),
  INDEX `fk_qm_user_session_qm_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_qm_user_session_qm_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `examination`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `examination` ;

CREATE TABLE IF NOT EXISTS `examination` (
  `exam_id` BIGINT NOT NULL,
  `exam_name` VARCHAR(100) NULL,
  `activation_start` TIMESTAMP NULL,
  `activation_end` TIMESTAMP NULL,
  `duration` FLOAT NULL,
  `created_by` BIGINT NOT NULL,
  `created_date` TIMESTAMP NULL,
  `updated_by` BIGINT NULL,
  `updated_date` TIMESTAMP NULL,
  `comment` VARCHAR(400) NULL,
  `min_score` DECIMAL NOT NULL,
  `max_score` DECIMAL NOT NULL,
  PRIMARY KEY (`exam_id`),
  INDEX `fk_examination_qm_user1_idx` (`created_by` ASC),
  INDEX `fk_examination_qm_user2_idx` (`updated_by` ASC),
  CONSTRAINT `fk_examination_qm_user1`
    FOREIGN KEY (`created_by`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_examination_qm_user2`
    FOREIGN KEY (`updated_by`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `examination_session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `examination_session` ;

CREATE TABLE IF NOT EXISTS `examination_session` (
  `session_id` BIGINT NOT NULL,
  `examination_id` BIGINT NOT NULL,
  `start_time` TIMESTAMP NULL,
  `end_time` TIMESTAMP NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`session_id`),
  INDEX `fk_examination_session_examination1_idx` (`examination_id` ASC),
  INDEX `fk_examination_session_qm_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_examination_session_examination1`
    FOREIGN KEY (`examination_id`)
    REFERENCES `examination` (`exam_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_examination_session_qm_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mstr_question_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mstr_question_types` ;

CREATE TABLE IF NOT EXISTS `mstr_question_types` (
  `type` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`type`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `question` ;

CREATE TABLE IF NOT EXISTS `question` (
  `question_id` BIGINT NOT NULL AUTO_INCREMENT,
  `question_text` VARCHAR(2000) NOT NULL,
  `question_type` VARCHAR(45) NOT NULL,
  `question_bank_id` BIGINT NOT NULL,
  `created_by` BIGINT NOT NULL,
  `created_date` TIMESTAMP NULL,
  `updated_by` BIGINT NULL,
  `updated_date` TIMESTAMP NULL,
  `access_type` VARCHAR(45) NOT NULL DEFAULT true,
  `display_index` INT NULL,
  `test_section_id` BIGINT NOT NULL,
  PRIMARY KEY (`question_id`),
  INDEX `fk_question_question_bank1_idx` (`question_bank_id` ASC),
  INDEX `fk_question_user_idx` (`created_by` ASC),
  INDEX `fk_question_updated_by_idx` (`updated_by` ASC),
  INDEX `fk_access_type_idx` (`access_type` ASC),
  INDEX `fk_question_mstr_question_types1_idx` (`question_type` ASC),
  INDEX `fk_question_test_section1_idx` (`test_section_id` ASC),
  CONSTRAINT `fk_question_question_bank1`
    FOREIGN KEY (`question_bank_id`)
    REFERENCES `question_bank` (`bank_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_created_by`
    FOREIGN KEY (`created_by`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_updated_by`
    FOREIGN KEY (`updated_by`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_access_type`
    FOREIGN KEY (`access_type`)
    REFERENCES `mstr_access_types` (`type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_mstr_question_types1`
    FOREIGN KEY (`question_type`)
    REFERENCES `mstr_question_types` (`type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_test_section1`
    FOREIGN KEY (`test_section_id`)
    REFERENCES `test_section` (`section_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `question_option`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `question_option` ;

CREATE TABLE IF NOT EXISTS `question_option` (
  `option_id` BIGINT NOT NULL,
  `option_text` VARCHAR(500) NULL,
  `explanation` VARCHAR(2000) NULL,
  `show_explanation` TINYINT(1) NULL,
  `display_index` INT NULL,
  `option_type` VARCHAR(45) NULL,
  `question_id` BIGINT NOT NULL,
  `created_by` BIGINT NOT NULL,
  `created_date` TIMESTAMP NULL,
  `updated_by` BIGINT NULL,
  `updated_date` TIMESTAMP NULL,
  `is_valid` TINYINT(1) NULL,
  PRIMARY KEY (`option_id`),
  INDEX `fk_question_option_question1_idx` (`question_id` ASC),
  CONSTRAINT `fk_question_option_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `question` (`question_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `score_card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `score_card` ;

CREATE TABLE IF NOT EXISTS `score_card` (
  `score_card_id` BIGINT NOT NULL,
  `examination_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `examination_session_id` BIGINT NOT NULL,
  `total_score` DECIMAL NULL,
  PRIMARY KEY (`score_card_id`),
  INDEX `fk_score_card_examination1_idx` (`examination_id` ASC),
  INDEX `fk_score_card_user1_idx` (`user_id` ASC),
  INDEX `fk_score_card_examination_session1_idx` (`examination_session_id` ASC),
  CONSTRAINT `fk_score_card_examination1`
    FOREIGN KEY (`examination_id`)
    REFERENCES `examination` (`exam_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_score_card_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_score_card_examination_session1`
    FOREIGN KEY (`examination_session_id`)
    REFERENCES `examination_session` (`session_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `answer` ;

CREATE TABLE IF NOT EXISTS `answer` (
  `answer_id` BIGINT NOT NULL,
  `answer_text` VARCHAR(1000) NULL,
  `file_type` VARCHAR(45) NULL,
  `file_data` BLOB NULL,
  `user_id` BIGINT NOT NULL,
  `question_id` BIGINT NOT NULL,
  `question_option_id` BIGINT NOT NULL,
  `score_card_id` BIGINT NOT NULL,
  PRIMARY KEY (`answer_id`),
  INDEX `fk_answer_user1_idx` (`user_id` ASC),
  INDEX `fk_answer_question1_idx` (`question_id` ASC),
  INDEX `fk_answer_question_option1_idx` (`question_option_id` ASC),
  INDEX `fk_answer_score_card1_idx` (`score_card_id` ASC),
  CONSTRAINT `fk_answer_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `question` (`question_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_question_option1`
    FOREIGN KEY (`question_option_id`)
    REFERENCES `question_option` (`option_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_score_card1`
    FOREIGN KEY (`score_card_id`)
    REFERENCES `score_card` (`score_card_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `question_master`;
INSERT INTO `user` (`user_id`, `user_name`, `password`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (1, 'admin', 'admin', 'admin@qm-gsi.com', 'Admin', NULL, 'User');
INSERT INTO `user` (`user_id`, `user_name`, `password`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (2, 'sabuj', '1234', 'sabuj.das@gmail.com', 'Sabuj', NULL, 'Das');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mstr_access_types`
-- -----------------------------------------------------
START TRANSACTION;
USE `question_master`;
INSERT INTO `mstr_access_types` (`type`, `description`) VALUES ('PUBLIC', 'Public');
INSERT INTO `mstr_access_types` (`type`, `description`) VALUES ('PROTECTED', 'Protected');
INSERT INTO `mstr_access_types` (`type`, `description`) VALUES ('PRIVATE', 'Private');

COMMIT;


-- -----------------------------------------------------
-- Data for table `role`
-- -----------------------------------------------------
START TRANSACTION;
USE `question_master`;
INSERT INTO `role` (`role_id`, `role_title`, `role_description`) VALUES (1, 'ROLE_ADMIN', 'System Administrator');
INSERT INTO `role` (`role_id`, `role_title`, `role_description`) VALUES (2, 'ROLE_USER', 'User');
INSERT INTO `role` (`role_id`, `role_title`, `role_description`) VALUES (3, 'ROLE_EXAMINER', 'Examiner');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_role_map`
-- -----------------------------------------------------
START TRANSACTION;
USE `question_master`;
INSERT INTO `user_role_map` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `user_role_map` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `user_role_map` (`user_id`, `role_id`) VALUES (2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mstr_question_types`
-- -----------------------------------------------------
START TRANSACTION;
USE `question_master`;
INSERT INTO `mstr_question_types` (`type`, `description`) VALUES ('TEXT', 'Text');
INSERT INTO `mstr_question_types` (`type`, `description`) VALUES ('SINGLE_SELECT', 'Single Selection');
INSERT INTO `mstr_question_types` (`type`, `description`) VALUES ('MULTI_SELECT', 'Multiple Selection');

COMMIT;

