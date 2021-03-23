DROP SCHEMA IF EXISTS oblig_jpa CASCADE;

CREATE SCHEMA oblig_jpa;
SET search_path TO oblig_jpa;

DROP TABLE IF EXISTS ansatt;
DROP TABLE IF EXISTS avdeling;

CREATE TABLE ansatt
(
	ansattId integer NOT NULL,
    brukernavn Char(4) NOT NULL,
    fornavn VARCHAR(30),
    etternavn VARCHAR(30),
    ansettelsedato DATE,
    stilling VARCHAR(30),
    maanedslonn integer,
    CONSTRAINT ansatt_pk PRIMARY KEY (ansattId)
    
    
  );  
INSERT INTO
  ansatt(ansattId, brukernavn, fornavn, etternavn, ansettelsedato, stilling, maanedslonn)
VALUES
    (1, 'Jofe', 'Joacim', 'Fredriksen', '14.02.21', 'Utvikler', 600000),
    (2, 'FyGe', 'Geir', 'Janssen', '24.01.20', 'Regnskapsfoerer', 800000 ),
    (3 ,'Kain', 'Kai', 'Nilson', '08.01.16', 'CEO', 1000000);


CREATE TABLE avdeling
(
	avdelingId integer NOT NULL,
	navn VARCHAR (30) NOT NULL,
	sjef integer NOT NULL,
	CONSTRAINT avdeling_pk PRIMARY KEY (avdelingId)
);

INSERT INTO
avdeling(navn,sjef)
VALUES
	('Dat100',1),
	('Dat102',2),
	('Dat107',3);
