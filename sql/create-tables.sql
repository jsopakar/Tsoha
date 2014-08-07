
--CREATE TABLE testi (
--	ID numeric(8) NOT NULL,
--	kuvaus varchar(40),
--	numero numeric(10,1),
--	PRIMARY KEY (ID)
--);

CREATE TABLE tuote (
	id SERIAL PRIMARY KEY,
	nimi varchar(64),
	kuvaus varchar(256),
	hinta DECIMAL(3,2),
	tuotetyyppi INTEGER,
	tuotenumero INTEGER
);

CREATE TABLE tayte (
	id SERIAL PRIMARY KEY,
	nimi varchar(64),
	kuvaus varchar(256),
	hinta DECIMAL(3,2),
	onkoLisatayte boolean
);

CREATE TABLE linkTuoteTayte (
	tuoteID INTEGER REFERENCES tuote(id),
	tayteID INTEGER REFERENCES tayte(id)
);
	
