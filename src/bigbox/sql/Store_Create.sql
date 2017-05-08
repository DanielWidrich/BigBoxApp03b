DROP DATABASE if EXISTS bigbox;
CREATE DATABASE bigbox;
USE bigbox;

CREATE TABLE stores (
  StoreID   INT            PRIMARY KEY  AUTO_INCREMENT,
  Name  	VARCHAR(25)		NOT NULL,
  Address	VARCHAR(50)		NOT NULL,
  City		VARCHAR(30)		NOT NULL,
  State 	CHAR(2)			NOT NULL,
  Zip		CHAR(5)			NOT NULL,
  StoreNum	CHAR(5)			NOT NULL,
  DivNum	CHAR(3)			NOT NULL,
  Sales		DECIMAL(10,2)	NOT NULL
);

INSERT INTO stores VALUES 
(1, 'Mason BigBox', '5711 Fields Ertel Rd.', 'Mason', 'OH', '45249', '00011', '001', '25000.00'), 
(2, 'Downtown BigBox', '9330 Main St.', 'Cincinnati', 'OH', '45202', '00255', '001', '27500.00'),
(3, 'Goshen BigBox', '6777 Goshen Rd.', 'Goshen', 'OH', '45122', '00172', '001', '32555.55'),
(4, 'Bridgetown BigBox', '3888 Race Rd.', 'Cincinnati', 'OH', '45211', '00075', '001', '21425.37'),
(5, 'Louisville BigBox', '1111 Washington St.', 'Louisville', 'KY', '40206', '00001', '111', '14432.77'),
(6, 'Lawrenceburg BigBox', '8000 Liberty St.', 'Louisville', 'KY', '40204', '00044', '111', '17555.11' );

GRANT SELECT, INSERT, DELETE, UPDATE
ON bigbox.*
TO bb_user@localhost -- #####@localhost allows ANY user with password 'sesame' to access this database/table
IDENTIFIED BY 'sesame';