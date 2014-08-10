INSERT INTO tuote(nimi, kuvaus, hinta, tuotetyyppi, tuotenumero) VALUES
	('Americano', 'Herkullinen pizza', 6.90, 1, 1);
INSERT INTO tuote(nimi, kuvaus, hinta, tuotetyyppi, tuotenumero) VALUES
	('Opera', 'Toinen pizza', 7.50, 1, 2);
INSERT INTO tuote(nimi, kuvaus, hinta, tuotetyyppi, tuotenumero) VALUES
	('Kebab ranskalaisilla', 'testikebab', 7.20, 2, 30);

INSERT INTO tayte(nimi, kuvaus, hinta, onkoLisatayte) VALUES
	('pizzapohja', 'Peruspohja jokaiselle pizzalle', 0.00, false);
INSERT INTO tayte(nimi, kuvaus, hinta, onkoLisatayte) VALUES
	('tomaattikastike', 'Perusraaka-aine pizzassa', 1.00, false);
INSERT INTO tayte(nimi, kuvaus, hinta, onkoLisatayte) VALUES
	('ananas', 'ananastäyte', 1.00, true);
INSERT INTO tayte(nimi, kuvaus, hinta, onkoLisatayte) VALUES
	('kebabliha', 'sekä kebabin että pizzan lihatäyte', 1.00, true);

INSERT INTO linkTuoteTayte(tuoteID, tayteID) VALUES
	(1, 1);
INSERT INTO linkTuoteTayte(tuoteID, tayteID) VALUES
	(1, 2);
INSERT INTO linkTuoteTayte(tuoteID, tayteID) VALUES
	(1, 3);
INSERT INTO linkTuoteTayte(tuoteID, tayteID) VALUES
	(2, 1);
INSERT INTO linkTuoteTayte(tuoteID, tayteID) VALUES
	(2, 2);
INSERT INTO linkTuoteTayte(tuoteID, tayteID) VALUES
	(3, 4);

INSERT INTO tilaus(asiakasnimi, puhelinnumero, osoite) VALUES
	('pekka', '012345789', 'Kotiosoite 10');

INSERT INTO tilauksentuote(tuoteID, tilausID, huomautuksia, hinta) VALUES
	(1, 1, 'ei huomautusta', 6.90);
INSERT INTO tilauksentuote(tuoteID, tilausID, huomautuksia, hinta) VALUES
	(3, 1, 'ketsuppia!', 6.90);

INSERT INTO yllapitotunnukset(tunnus, salasana, tyyppi) VALUES
	('omistaja', 'salainen', 1);
INSERT INTO yllapitotunnukset(tunnus, salasana, tyyppi) VALUES
	('kuski', 'enkerro', 2);
