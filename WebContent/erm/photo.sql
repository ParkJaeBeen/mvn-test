
/* Drop Triggers */

DROP TRIGGER TRI_photo_board_pb_num;



/* Drop Tables */

DROP TABLE photo_board CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_photo_board_pb_num;




/* Create Sequences */

CREATE SEQUENCE SEQ_photo_board_pb_num INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE photo_board
(
	pb_num number(10,0) NOT NULL,
	pb_title varchar2(300) NOT NULL,
	pb_content clob NOT NULL,
	pb_img1 varchar2(300),
	pb_img2 varchar2(300),
	credat char(10) NOT NULL,
	cretim char(8) NOT NULL,
	creusr number(10,0) NOT NULL,
	moddat char(10) NOT NULL,
	modtim char(8) NOT NULL,
	modusr number(10,0) NOT NULL,
	pb_cnt number(10,0) DEFAULT 0 NOT NULL,
	PRIMARY KEY (pb_num)
);



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_photo_board_pb_num BEFORE INSERT ON photo_board
FOR EACH ROW
BEGIN
	SELECT SEQ_photo_board_pb_num.nextval
	INTO :new.pb_num
	FROM dual;
END;

/




