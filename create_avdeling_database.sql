DROP TABLE IF EXISTS avdeling;

CREATE TABLE avdeling
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