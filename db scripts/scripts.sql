create database mvc;

use mvc;

CREATE TABLE `users` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(20) NULL DEFAULT NULL,
`email` VARCHAR(50) NOT NULL,
`password` VARCHAR(200) NULL DEFAULT NULL,
`mobile_no` VARCHAR(12) NULL DEFAULT NULL,
`city` VARCHAR(50) NULL DEFAULT NULL,
`status` TINYINT(4) NOT NULL DEFAULT '1',
`photo` LONGBLOB NULL,
`sign` LONGBLOB NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `email` (`email`)
)

CREATE TABLE `user_roles` (
`role_id` INT(11) NOT NULL AUTO_INCREMENT,
`user_id` INT(11) NOT NULL,
`role` VARCHAR(45) NULL DEFAULT NULL,
PRIMARY KEY (`role_id`),
UNIQUE INDEX `user_id` (`user_id`, `role`),
CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);
