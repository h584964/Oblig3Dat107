DROP SCHEMA IF EXISTS oblig_jpa CASCADE;
CREATE SCHEMA oblig_jpa;
SET search_path TO oblig_jpa;

DROP TABLE IF EXISTS ansatt;
DROP TABLE IF EXISTS avdeling;

CREATE TABLE ansatt
(
	ansattId SERIAL,								--ansattId integer NOT NULL,
    brukernavn Char(4) NOT NULL,
    fornavn VARCHAR(30),
    etternavn VARCHAR(30),
    ansettelsedato DATE,
    stilling VARCHAR(30),
    maanedslonn integer,
    avdelingId integer NOT NULL,
    CONSTRAINT ansatt_pk PRIMARY KEY (ansattId)
   
 
    
    
  );
  
  CREATE TABLE avdeling
(
	avdelingId SERIAL,
	navn VARCHAR (30) NOT NULL,
	sjef integer NOT NULL,
	CONSTRAINT avdeling_pk PRIMARY KEY (avdelingId)
);
 
--INSERT INTO
--  ansatt(ansattId, brukernavn, fornavn, etternavn, ansettelsedato, stilling, maanedslonn, avdelingId) 
--VALUES
  -- (1, 'Jofe', 'Joacim', 'Fredriksen', '14.02.21', 'Utvikler', 600000, 1),
  -- (2, 'FyGe', 'Geir', 'Janssen', '24.01.20', 'Regnskapsfoerer', 850000, 1 ),
  -- (3, 'Kain', 'Kai', 'Nilson', '08.01.16', 'CEO', 1000000, 1),
  -- (4, 'LaMa', 'Lars', 'Lama', '14.03.21', 'SCRUM Master', 800000, 2),
  -- (5, 'HaKa', 'Hans', 'Karlsen', '14.02.21', 'Senior utvikler', 750000, 2),
  -- (6, 'Guma', 'Marcus', 'Gundersen', '24.03.21', 'Utvikler', 600000, 2),
  -- (7, 'Joke', 'Joakim', 'Olsen', '04.06.16', 'Markedsjef', 800000, 3),
  -- (8, 'Jack', 'Jack', 'Willson', '14.06.16', 'Senior utvikler', 750000, 3),
  -- (9, 'Akos', 'Alex', 'Svendsen', '14.06.16', 'Økonom', 850000, 3),
  -- (10, 'TaHa', 'Thale', 'Halvorsen', '14.06.20', 'Utvikler', 600000, 1);


--Ny innsettning av verdier, har tatt bort ansattId fordi det skapte mye problmer     
INSERT INTO
  ansatt(brukernavn, fornavn, etternavn, ansettelsedato, stilling, maanedslonn, avdelingId) 
VALUES
    ('Jofe', 'Joacim', 'Fredriksen', '14.02.21', 'Utvikler', 600000, 1),
    ('FyGe', 'Geir', 'Janssen', '24.01.20', 'Regnskapsfoerer', 850000, 1 ),
    ('Kain', 'Kai', 'Nilson', '08.01.16', 'CEO', 1000000, 1),
    ('LaMa', 'Lars', 'Lama', '14.03.21', 'SCRUM Master', 800000, 2),
    ('HaKa', 'Hans', 'Karlsen', '14.02.21', 'Senior utvikler', 750000, 2),
    ('Guma', 'Marcus', 'Gundersen', '24.03.21', 'Utvikler', 600000, 2),
    ('Joke', 'Joakim', 'Olsen', '04.06.16', 'Markedsjef', 800000, 3),
    ('Jack', 'Jack', 'Willson', '14.06.16', 'Senior utvikler', 750000, 3),
    ('Akos', 'Alex', 'Svendsen', '14.06.16', 'Økonom', 850000, 3),
    ('TaHa', 'Thale', 'Halvorsen', '14.06.20', 'Utvikler', 600000, 1);


INSERT INTO
avdeling(navn,sjef)
VALUES
	('Dat100', 1),
	('Dat102', 2),
	('Dat107', 3);
	

--Endrer ansatt sin tabell
ALTER TABLE ansatt
ADD CONSTRAINT avdeling_fk
FOREIGN KEY (avdelingId) REFERENCES avdeling(avdelingId);

--Endrer avdeling sin tabell
ALTER TABLE avdeling
ADD FOREIGN KEY (sjef) REFERENCES ansatt(ansattId);

