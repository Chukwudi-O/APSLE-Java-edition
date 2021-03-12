CREATE DATABASE IF NOT EXISTS `apsle` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `apsle`;

/* Creating all relevant tables */

CREATE TABLE `studentlogins`(
`id` BIGINT NOT NULL AUTO_INCREMENT,
`username` varchar(64) DEFAULT NULL,
`password` varchar(64) DEFAULT NULL,
`classNumber` int(64) DEFAULT NULL,
`gradeNumber` int(64) DEFAULT NULL,
`teacherName` varchar(64) DEFAULT NULL,
PRIMARY KEY(`id`)
);

CREATE TABLE `teacherlogins`(
`id` BIGINT NOT NULL AUTO_INCREMENT,
`username` varchar(64) DEFAULT NULL,
`password` varchar(64) DEFAULT NULL,
`classNumber` int(64) DEFAULT NULL,
`gradeNumber` int(64) DEFAULT NULL,
PRIMARY KEY(`id`)
);

CREATE TABLE `adminlogins`(
`id` BIGINT NOT NULL AUTO_INCREMENT,
`username` varchar(64) DEFAULT NULL,
`password` varchar(64) DEFAULT NULL,
PRIMARY KEY(`id`)
);

CREATE TABLE `teacheruploads`(
`id` BIGINT NOT NULL AUTO_INCREMENT,
`fileName` varchar(64) DEFAULT NULL,
`fileExtension` varchar(64) DEFAULT NULL,
`dateUploaded` varchar(64) DEFAULT NULL,
`subject` varchar(64) DEFAULT NULL,
`gradeNumber` int(64) DEFAULT NULL,
`classNumber` int(64) DEFAULT NULL,
`typeOfUpload` varchar(64) DEFAULT NULL,
`uploadedBy` varchar(64) DEFAULT NULL,
PRIMARY KEY(`id`)
);

/* Inserting values into adminlogins table */
INSERT INTO `adminlogins` (`id`, `username`, `password`) VALUES
(1, 'chuck', 'ojuro');

/* Inserting values into teacherlogins table */
INSERT INTO `teacherlogins` (`id`, `username`, `password`,`classNumber`, `gradeNumber`) VALUES
(1, 'chuck', 'tyler',1,1);
INSERT INTO `teacherlogins` (`id`, `username`, `password`,`classNumber`, `gradeNumber`) VALUES
(2, 'chevil', 'mitchell',1,2);
INSERT INTO `teacherlogins` (`id`, `username`, `password`,`classNumber`, `gradeNumber`) VALUES
(3, 'noel', 'powell',1,3);
INSERT INTO `teacherlogins` (`id`, `username`, `password`,`classNumber`, `gradeNumber`) VALUES
(4, 'tatanya', 'lynch',1,4);
INSERT INTO `teacherlogins` (`id`, `username`, `password`,`classNumber`, `gradeNumber`) VALUES
(5, 'shemar', 'nelson',1,5);
INSERT INTO `teacherlogins` (`id`, `username`, `password`,`classNumber`, `gradeNumber`) VALUES
(6, 'teacher61', 'teacher61',1,6);

/* Inserting values into studentlogins table */

INSERT INTO `studentlogins` (`id`, `username`, `password`,`classNumber`, `gradeNumber`, `teacherName`) VALUES
(1, 'student11', 'student11',1,1, 'chuck');
INSERT INTO `studentlogins` (`id`, `username`, `password`,`classNumber`, `gradeNumber`, `teacherName`) VALUES
(2, 'student21', 'student21',1,2, 'chevil');
INSERT INTO `studentlogins` (`id`, `username`, `password`,`classNumber`, `gradeNumber`, `teacherName`) VALUES
(3, 'student31', 'student31',1,3, 'noel');
INSERT INTO `studentlogins` (`id`, `username`, `password`,`classNumber`, `gradeNumber`, `teacherName`) VALUES
(4, 'student41', 'student41',1,4, 'tatanya');
INSERT INTO `studentlogins` (`id`, `username`, `password`,`classNumber`, `gradeNumber`, `teacherName`) VALUES
(5, 'student51', 'student51',1,5, 'shamar');
INSERT INTO `studentlogins` (`id`, `username`, `password`,`classNumber`, `gradeNumber`, `teacherName`) VALUES
(6, 'student61', 'student61',1,6, 'teacher61');