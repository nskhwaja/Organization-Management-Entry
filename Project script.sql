DROP DATABASE IF EXISTS FinalProject;

CREATE DATABASE FinalProject;

USE FinalProject;
CREATE TABLE StudentOrganization (
  orgname VARCHAR(77),
  description VARCHAR(500),
  meetingdate VARCHAR(77),
  meetingtime VARCHAR(77),
  contactinformation VARCHAR(77),
  
  
  PRIMARY KEY(orgname) 
);

CREATE TABLE Year (
Year VARCHAR (4),
semester VARCHAR(3),

Primary Key (semester)

);

CREATE TABLE Event (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  title VARCHAR(77),
  description VARCHAR(500),
  location VARCHAR(77),
  presenter VARCHAR(77),
  company VARCHAR(77),
  startDate VARCHAR(15),
  startTime VARCHAR(15),
  timeofday1 VARCHAR(2),
  endDate VARCHAR(15),
  endTime VARCHAR(15),
  timeofday2 VARCHAR(2),
  attendence VARCHAR(4),
  orgname VARCHAR (77),
  semester VARCHAR(3),
  
  FOREIGN KEY (orgname) REFERENCES StudentOrganization(orgname),
  FOREIGN KEY (semester) REFERENCES Year(semester),
  PRIMARY KEY(id) 
);

CREATE TABLE Advisor (
  adhawkid VARCHAR(77),
  firstname VARCHAR(77),
  lastname VARCHAR (77),
  position VARCHAR (77),
  contactinformation VARCHAR(77),
  orgname VARCHAR  (77),
  semester VARCHAR (3),
  FOREIGN KEY (semester) REFERENCES Year(semester),
  FOREIGN KEY (orgname) REFERENCES StudentOrganization(orgname),
  PRIMARY KEY(adhawkid) 
);

CREATE TABLE Member (
  hawkid	VARCHAR(77),
  firstname VARCHAR(77),
  lastname 	VARCHAR(77),
  major1	VARCHAR(77),
  major2	VARCHAR(77),
  membertype VARCHAR (77),
  orgname 	VARCHAR (77),
  semester VARCHAR (3),
  
  PRIMARY KEY(hawkid, orgname),
  FOREIGN KEY (orgname) REFERENCES StudentOrganization(orgname),
  FOREIGN KEY (semester) REFERENCES Year(semester)
);
/*CREATE TABLE Membership (
  hawkid	VARCHAR(77),
  orgname VARCHAR(77),
  semester VARCHAR(77),
  PRIMARY KEY (hawkid,orgname),
    FOREIGN KEY (hawkid) REFERENCES Member(hawkid),
     FOREIGN KEY (orgname) REFERENCES StudentOrganization(orgname)
    
);*/
CREATE TABLE User (
  username VARCHAR(77) NOT NULL PRIMARY KEY,
  password VARCHAR(77) NOT NULL


);
CREATE TABLE UserInRoles (
  Role VARCHAR(77) NOT NULL,
  username VARCHAR(77) NOT NULL,
   PRIMARY KEY (username, Role),
  FOREIGN KEY (username) REFERENCES User(username)


);

CREATE TABLE Question (
question VARCHAR (500),
orgname VARCHAR (77),
semester VARCHAR (3),

PRIMARY KEY (question, orgname),
FOREIGN KEY (semester) REFERENCES Year(semester),
FOREIGN KEY (orgname) REFERENCES StudentOrganization(orgname)

);

CREATE TABLE Answer  (
id INT UNSIGNED NOT NULL AUTO_INCREMENT NOT NULL PRIMARY KEY,
response VARCHAR (500),
question VARCHAR (500),
orgname VARCHAR(77),

FOREIGN KEY (question) REFERENCES Question(question)
FOREIGN KEY (orgname) REFERENCES StudentOrganization(orgname)

);


-- Create project_user and grant privileges

DELIMITER //
CREATE PROCEDURE drop_user_if_exists()
BEGIN
    DECLARE userCount BIGINT DEFAULT 0 ;
    SELECT COUNT(*) INTO userCount FROM mysql.user
    WHERE User = 'project_user' and  Host = 'localhost';

    IF userCount > 0 THEN
        DROP USER project_user@localhost;
    END IF;
END ; //
DELIMITER ;

CALL drop_user_if_exists() ;

CREATE USER project_user@localhost IDENTIFIED BY 'sesame';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP
ON FinalProject.*
TO project_user@localhost;