DROP DATABASE `ponab_statistical_reports`;
CREATE DATABASE IF NOT EXISTS `ponab_statistical_reports`;
USE `ponab_statistical_reports`;

CREATE TABLE IF NOT EXISTS `communication_distance` (
  `id`     BIGINT(21) NOT NULL AUTO_INCREMENT,
  `number` TINYINT    NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `station` (
  `id`   BIGINT(21)   NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `stage` (
  `id`                        BIGINT(21)   NOT NULL AUTO_INCREMENT,
  `id_first_station`          BIGINT(21)   NOT NULL,
  `id_second_station`         BIGINT(21)   NOT NULL,
  `id_communication_distance` BIGINT(21)   NOT NULL,
  `name`                      VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_first_station`) REFERENCES `station` (`id`),
  FOREIGN KEY (`id_second_station`) REFERENCES `station` (`id`),
  FOREIGN KEY (`id_communication_distance`) REFERENCES `communication_distance` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `sector` (
  `id`       BIGINT(21)   NOT NULL AUTO_INCREMENT,
  `id_stage` BIGINT(21)   NOT NULL,
  `title`    VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_stage`) REFERENCES `stage` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `ponab_system` (
  `id`       BIGINT(21)   NOT NULL AUTO_INCREMENT,
  `id_stage` BIGINT(21)   NOT NULL,
  `location` VARCHAR(200) NOT NULL,
  `title`    VARCHAR(200) NOT NULL,
  `option`   VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_stage`) REFERENCES `stage` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `vagon_laboratory` (
  `id`   BIGINT(21)   NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `track_circuit` (
  `id`                        BIGINT(21)   NOT NULL AUTO_INCREMENT,
  `id_sector`                 BIGINT(21)   NOT NULL,
  `id_stage`                  BIGINT(21)            DEFAULT NULL,
  `id_station`                BIGINT(21)            DEFAULT NULL,
  `id_communication_distance` BIGINT(21)            DEFAULT NULL,
  `picket`                    DOUBLE       NOT NULL,
  `name`                      VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_sector`) REFERENCES `sector` (`id`),
  FOREIGN KEY (`id_station`) REFERENCES `station` (`id`),
  FOREIGN KEY (`id_stage`) REFERENCES `stage` (`id`),
  FOREIGN KEY (`id_communication_distance`) REFERENCES `communication_distance` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `planned_trip` (
  `id`                  BIGINT(21) NOT NULL AUTO_INCREMENT,
  `id_sector`           BIGINT(21) NOT NULL,
  `id_vagon_laboratory` BIGINT(21) NOT NULL,
  `begin_date` TIMESTAMP,
  `end_date`   TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `inspection_trip` (
  `id`                  BIGINT(21) NOT NULL AUTO_INCREMENT,
  `id_sector`           BIGINT(21) NOT NULL,
  `id_vagon_laboratory` BIGINT(21) NOT NULL,
  `begin_date` TIMESTAMP,
  `end_date`   TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_sector`) REFERENCES `sector` (`id`),
  FOREIGN KEY (`id_vagon_laboratory`) REFERENCES `vagon_laboratory` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `ponab_remark` (
  `id`              BIGINT(21)   NOT NULL AUTO_INCREMENT,
  `id_inspection`   BIGINT(21)   NOT NULL,
  `id_ponab_system` BIGINT(21)   NOT NULL,
  `even`            TINYINT(1)   NOT NULL,
  `repeatable`      TINYINT(1)   NOT NULL,
  `note`            VARCHAR(200) NOT NULL,
  `creation_date`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_inspection`) REFERENCES `inspection_trip` (`id`),
  FOREIGN KEY (`id_ponab_system`) REFERENCES `ponab_system` (`id`),
  FOREIGN KEY (`id_stage`) REFERENCES `stage` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `als_remark` (
  `id`               BIGINT(21) NOT NULL AUTO_INCREMENT,
  `id_inspection`    BIGINT(21) NOT NULL,
  `id_track_circuit` BIGINT(21) NULL,
  `note`             VARCHAR(200)        DEFAULT NULL,
  `even`             TINYINT(1) NOT NULL,
  `repeatable`       TINYINT(1) NOT NULL,
  `creation_date`    TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_inspection`) REFERENCES `inspection_trip` (`id`),
  FOREIGN KEY (`id_track_circuit`) REFERENCES `track_circuit` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;