-- 
-- Originally created by Gzus ; posted on slack.
-- 
-- SEL DATABASE SQL FILE v5
-- 

DROP DATABASE SELv3;
CREATE DATABASE SELv3;
USE SELv5;

DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Books;
DROP TABLE IF EXISTS Posts;

CREATE TABLE Users (
    UserID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(25) NOT NULL,
    LastName VARCHAR(35) NOT NULL,
    UserName VARCHAR(25) NOT NULL UNIQUE,
    EMail VARCHAR(35) NOT NULL,
    Phone VARCHAR(15) NULL,
    Password VARCHAR(25) NOT NULL
);

CREATE TABLE Books (
    BookID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Price DECIMAL(5,2) NOT NULL,
    Title VARCHAR(75) NOT NULL,
    ISBN VARCHAR(20) NOT NULL,
    AuthorFirst VARCHAR(25) NOT NULL,
    AuthorLast  VARCHAR(35) NOT NULL,
    Subject VARCHAR(18) NULL,
    Class VARCHAR(15) NOT NULL,
    State VARCHAR(12) NOT NULL
);

CREATE TABLE Posts (
    PostID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    PostDate DATE NOT NULL,
    UserID INT NOT NULL,
    BookID INT NOT NULL,
    ExperationDate DATE NOT NULL
);


-- 
-- Example Data 
-- 

/* The first parameter is ZERO (0) because it will auto increment as data is added. However, it will not 
   change it ID if some records are deleted (i.e. once assighned they will keep their value). */

-- Users

INSERT INTO Users VALUES (0, "John", "Doe", "john-doe", "john@doe.com", "323-675-9982", "password");
INSERT INTO Users VALUES (0, "Jane", "Smith", "jane-smith", "jane@smith.com", "323-123-1234", "password");
INSERT INTO Users VALUES (0, "Kelly", "Mill", "kelly-mill", "kelly@mill.com", "323-592-7574", "password");
INSERT INTO Users VALUES (0, "Joe", "Davis", "joe-davis", "joe@davis.com", NULL, "password");
INSERT INTO Users VALUES (0, "Ashley", "Lopez", "ashley-lopez", "ashley@lopez.com", NULL, "password");

-- Books

INSERT INTO Books VALUES (0, 65.99, "Algorithms", "123456789", "James", "Corem", "Computer Science", "CS-3112", "Good");
INSERT INTO Books VALUES (0, 1.99, "Ethical Issues of Computing", "123456789", "Mark", "Sarge", "Computer Science", "CS-3801", "New");
INSERT INTO Books VALUES (0, 75.55, "Discrete Math", "123456789", "Someome", "Mathy", "Mathematicse", "MATH-2148", "Fair");
INSERT INTO Books VALUES (0, 50.55, "Software Eng.", "123456789", "Jose", "Macias", "Computer Science", "CS-3337", "New");
INSERT INTO Books VALUES (0, 95.99, "Automata Theory", "123456789", "Richard", "Cross", "Computer Science", "CS-3186", "Fair");
INSERT INTO Books VALUES (0, 45.85, "Web Dev", "123456789", "Eric", "Liao", "Computer Science", "CS-3220", "Good");
INSERT INTO Books VALUES (0, 15.75, "Roots of Rock", "123456789", "Debra", "Something", "Music", "MUS-1520", "Good");

-- Posts

INSERT INTO Posts VALUES (0, CURDATE(), 18, 1, ADDDATE(CURDATE(), 1));
INSERT INTO Posts VALUES (0, CURDATE(), 19, 2, ADDDATE(CURDATE(), 2));
INSERT INTO Posts VALUES (0, CURDATE(), 20, 3, ADDDATE(CURDATE(), 4));
INSERT INTO Posts VALUES (0, CURDATE(), 21, 4, ADDDATE(CURDATE(), 6));
INSERT INTO Posts VALUES (0, CURDATE(), 23, 5, ADDDATE(CURDATE(), 5));
INSERT INTO Posts VALUES (0, CURDATE(), 23, 6, ADDDATE(CURDATE(), 2));
INSERT INTO Posts VALUES (0, CURDATE(), 18, 7, ADDDATE(CURDATE(), 5));
