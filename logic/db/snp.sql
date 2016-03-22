-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.19-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO,ANSI_QUOTES' */;


--
-- Create schema snp
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ snp;
USE snp;

--
-- Table structure for table "snp"."permissions"
--

DROP TABLE IF EXISTS "permissions";
CREATE TABLE "permissions" (
  "ID" int(10) unsigned NOT NULL auto_increment,
  "DESCRIPTION" varchar(256) NOT NULL default '',
  PRIMARY KEY  ("ID")
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table "snp"."permissions"
--

/*!40000 ALTER TABLE "permissions" DISABLE KEYS */;
INSERT INTO "permissions" ("ID","DESCRIPTION") VALUES 
 (1,'Test permission');
/*!40000 ALTER TABLE "permissions" ENABLE KEYS */;


--
-- Table structure for table "snp"."role_to_permission"
--

DROP TABLE IF EXISTS "role_to_permission";
CREATE TABLE "role_to_permission" (
  "ROLE_ID" varchar(45) NOT NULL default '',
  "PERMISSION_ID" int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  ("ROLE_ID","PERMISSION_ID"),
  KEY "FK_role_to_permission_P" ("PERMISSION_ID"),
  CONSTRAINT "FK_role_to_permission_P" FOREIGN KEY ("PERMISSION_ID") REFERENCES "permissions" ("ID") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "FK_role_to_permission_R" FOREIGN KEY ("ROLE_ID") REFERENCES "roles" ("ROLE") ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table "snp"."role_to_permission"
--

/*!40000 ALTER TABLE "role_to_permission" DISABLE KEYS */;
INSERT INTO "role_to_permission" ("ROLE_ID","PERMISSION_ID") VALUES 
 ('ROLE_ADMIN',1);
/*!40000 ALTER TABLE "role_to_permission" ENABLE KEYS */;


--
-- Table structure for table "snp"."roles"
--

DROP TABLE IF EXISTS "roles";
CREATE TABLE "roles" (
  "ROLE" varchar(45) NOT NULL default '',
  PRIMARY KEY  ("ROLE")
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table "snp"."roles"
--

/*!40000 ALTER TABLE "roles" DISABLE KEYS */;
INSERT INTO "roles" ("ROLE") VALUES 
 ('ROLE_ADMIN'),
 ('USER');
/*!40000 ALTER TABLE "roles" ENABLE KEYS */;


--
-- Table structure for table "snp"."user_to_role"
--

DROP TABLE IF EXISTS "user_to_role";
CREATE TABLE "user_to_role" (
  "USER_ID" int(10) unsigned NOT NULL auto_increment,
  "ROLE_ID" varchar(45) NOT NULL default '',
  PRIMARY KEY  ("USER_ID","ROLE_ID"),
  KEY "FK_user_to_role_R" ("ROLE_ID"),
  CONSTRAINT "FK_user_to_role_U" FOREIGN KEY ("USER_ID") REFERENCES "users" ("id") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "FK_user_to_role_R" FOREIGN KEY ("ROLE_ID") REFERENCES "roles" ("ROLE") ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table "snp"."user_to_role"
--

/*!40000 ALTER TABLE "user_to_role" DISABLE KEYS */;
INSERT INTO "user_to_role" ("USER_ID","ROLE_ID") VALUES 
 (1,'ROLE_ADMIN');
/*!40000 ALTER TABLE "user_to_role" ENABLE KEYS */;


--
-- Table structure for table "snp"."users"
--

DROP TABLE IF EXISTS "users";
CREATE TABLE "users" (
  "id" int(10) unsigned NOT NULL auto_increment,
  "first_name" varchar(45) NOT NULL default '',
  "last_name" varchar(45) NOT NULL default '',
  "middle_name" varchar(45) NOT NULL default '',
  "login_id" varchar(45) NOT NULL default '',
  "password" varchar(45) NOT NULL default '',
  "birth_date" date DEFAULT NULL,
  "gender" enum('M','F') DEFAULT 'M',      
  PRIMARY KEY  ("id"),
  UNIQUE KEY "UNIQUE_LOGIN_ID" ("login_id")
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table "snp"."users"
--

/*!40000 ALTER TABLE "users" DISABLE KEYS */;
INSERT INTO "users" ("id","first_name","last_name","middle_name","login_id","password") VALUES 
 (1,'User','Test','N','user1','pwd1');
/*!40000 ALTER TABLE "users" ENABLE KEYS */;

--
-- Table structure for table "snp"."training"
--

DROP TABLE IF EXISTS "training";
CREATE TABLE "training" (
  "ID" int(10) unsigned NOT NULL auto_increment,
  "USER_ID" int(10) unsigned NOT NULL default 0,
  "NAME" varchar(45) NOT NULL default '',
  "DESCRIPTION" varchar(255) NULL,
  PRIMARY KEY  ("ID"),
  KEY "FK_user_to_training_U" ("USER_ID"),
  CONSTRAINT "FK_user_to_training_U" FOREIGN KEY ("USER_ID") REFERENCES "users" ("id") ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table "snp"."training"
--

/*!40000 ALTER TABLE "users" DISABLE KEYS */;
INSERT INTO "training" ("id","user_id","name","description") VALUES
 (null,1,'First test training','Some training desription');
/*!40000 ALTER TABLE "users" ENABLE KEYS */;

--
-- Table structure for table "snp"."training_data"
--

DROP TABLE IF EXISTS "training_data";
CREATE TABLE "training_data" (
  "TRAINING_ID" int(10) unsigned NOT NULL auto_increment,
  "DATA" blob NULL,
  KEY "FK_training_to_data" ("TRAINING_ID"),
  CONSTRAINT "FK_training_to_data" FOREIGN KEY ("TRAINING_ID") REFERENCES "training" ("id") ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
