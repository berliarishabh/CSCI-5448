Create database MRS;

use MRS;

CREATE TABLE `UserRole_tbl` (
  `userRoleId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userRoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO USERROLE_TBL(name) values('ADMIN');
INSERT INTO USERROLE_TBL(name) values('MODERATOR');
INSERT INTO USERROLE_TBL(name) values('CRITIC');
INSERT INTO USERROLE_TBL(name) values('USER');

SELECT * FROM USERROLE_TBL;

DROP TABLE USERROLE_TBL;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

insert into user_tbl(userRoleId, name, password, username, emailAddress)
values(4, 'Rishabh', 'Huddar', 'ribe3261', 'ribe3261@colorado.edu');

insert into user_tbl(userRoleId, name, password, username, emailAddress)
values(1, 'Shiva', 'sck', 'shgu4085', 'shgu4085@colorado.edu');

insert into user_tbl(userRoleId, name, password, username, emailAddress)
values(3, 'Omkar', 'omse', 'omse6306', 'omse6306@colorado.edu');

SELECT * FROM USER_TBL;
SELECT * FROM User_tbl;

DROP TABLE USER_TBL;

DROP TABLE MOVIE_TBL;
CREATE TABLE `Movie_tbl` (
  `movieId` int(11) NOT NULL AUTO_INCREMENT,
  `movieName` varchar(100) DEFAULT NULL,
  `releaseYear` int(5) DEFAULT NULL,
  `genre` varchar(20) DEFAULT NULL,
  `aggregateRating` float(5) DEFAULT NULL,
  `numberOfUsersRated` int(5) DEFAULT NULL,
  `numberOfCriticsRated` int(5) DEFAULT NULL,
  `approvalState` varchar(2) DEFAULT NULL,
  `imageLocation` varchar(100) DEFAULT NULL,
  `movieDescription` varchar(600) DEFAULT NULL,
  
  PRIMARY KEY (`movieId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into movie_tbl(movieName, releaseYear, genre, aggregateRating, numberOfUsersRated, numberOfCriticsRated, approvalState,imageLocation, movieDescription)
values('Shawshank Redemption', 1994, 'Drama', 95, 100, 100, 'A','dummy/shawshank.jpeg', 
'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.');

insert into movie_tbl(movieName, releaseYear, genre, aggregateRating, numberOfUsersRated, numberOfCriticsRated, approvalState,imageLocation, movieDescription)
values('Forrest Gump', 2014, 'Comedy', 0, 0, 0, 'A','dummy/forrestgump.jpeg', 
'Sed ut perspiciatis unde omnis iste natus error voluptatem doloremque.');

insert into movie_tbl(movieName, releaseYear, genre, aggregateRating, numberOfUsersRated, numberOfCriticsRated, approvalState,imageLocation, movieDescription)
values('October Sky', 1999, 'Biography', 75, 10, 20, 'A','dummy/octobersky.jpeg', 
'');

insert into movie_tbl(movieName, releaseYear, genre, aggregateRating, numberOfUsersRated, numberOfCriticsRated, approvalState,imageLocation, movieDescription)
values('Snatch', 1994, 'Comedy', 68, 40, 70, 'A','dummy/snatch.jpeg', 
'Unscrupulous boxing promoters, violent bookmakers, a Russian gangster, incompetent amateur robbers, and supposedly Jewish jewelers fight to track down a priceless stolen diamond. ');


SELECT * FROM MOVIE_TBL;

DROP TABLE REVIEW_TBL;
CREATE TABLE `Review_tbl` (
	`reviewId` int(11) NOT NULL AUTO_INCREMENT, 
    `rating` float(5) DEFAULT NULL,
    `comment` varchar(300) DEFAULT NULL,
    `movieId` int(11) DEFAULT NULL,
    `flag` int(1) DEFAULT NULL,
    `userId` int(11) DEFAULT NULL,
    `nameUser` varChar(50) DEFAULT NULL,
    
  PRIMARY KEY (`reviewId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
insert into review_tbl(rating, comment, movieId, flag, userId, nameUser)
values(78,'Good Movie', 1, 0, 1, 'Rishabh');
SELECT * FROM REVIEW_TBL;
insert into review_tbl(rating, comment, movieId, flag, userId, nameUser)
values(88,'Gud Movie', 1, 0, 3, 'Omkar');
SELECT * FROM MOVIE_TBL WHERE releaseYear = null;
# A BIG TODO:
# Finish this
# Get it verified
# And then Run
# (Press CTRL + / for the following)
-- CREATE TABLE `Movie_tbl` (
--   `movieId`
--   `movieName`
--   `releaseYear`
--   `genre`
--   `aggregateRating`
--   `numberOfUsersRated`
--   `numberOfCriticsRated`
--   `approvalState`
-- 
-- 
--   `userRoleId` int(11) DEFAULT NULL,
--   `name` varchar(100) NOT NULL,
--   `password` varchar(100) DEFAULT NULL,
--   `username` varchar(100) NOT NULL,
--   `emailAddress` varchar(100) DEFAULT NULL,
--   PRIMARY KEY (`userId`),
--   KEY `userRoleId` (`userRoleId`),
--   CONSTRAINT `user_tbl_ibfk_1` FOREIGN KEY (`userRoleId`) REFERENCES `UserRole_tbl` (`userRoleId`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
-- 

-- CREATE TABLE `Review_tbl` (
-- 	
-- 	`reviewId`
--     `rating`
--     `comment`
--     `flag`
--     `userId`
--     `movieId`
-- 	
-- 
--   `userId` int(11) NOT NULL AUTO_INCREMENT,
--   `userRoleId` int(11) DEFAULT NULL,
--   `name` varchar(100) NOT NULL,
--   `password` varchar(100) DEFAULT NULL,
--   `username` varchar(100) NOT NULL,
--   `emailAddress` varchar(100) DEFAULT NULL,
--   PRIMARY KEY (`userId`),
--   KEY `userRoleId` (`userRoleId`),
--   CONSTRAINT `user_tbl_ibfk_1` FOREIGN KEY (`userRoleId`) REFERENCES `UserRole_tbl` (`userRoleId`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
-- 


# What about another table for notifications?
# How are we going to implement that?

