Create database MRS;

use MRS;

CREATE TABLE `UserRole_tbl` (
  `userRoleId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userRoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

SHOW tables;

CREATE TABLE `User_tbl` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userRoleId` int(11) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  `emailAddress` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `userRoleId` (`userRoleId`),
  CONSTRAINT `user_tbl_ibfk_1` FOREIGN KEY (`userRoleId`) REFERENCES `UserRole_tbl` (`userRoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


