DROP DATABASE IF EXISTS friends_of_man;
CREATE DATABASE IF NOT EXISTS friends_of_man;
USE friends_of_man;

CREATE TABLE IF NOT EXISTS HomePets (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name_animal VARCHAR(255),
    type_animal VARCHAR(255),
    birth_date DATE
);

SELECT * FROM HomePets;

CREATE TABLE IF NOT EXISTS PackAnimals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name_animal VARCHAR(255),
    type_animal VARCHAR(255),
    birth_date DATE
);

SELECT * FROM PackAnimals;

CREATE TABLE IF NOT EXISTS Dogs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_animal INT,
    name_animal VARCHAR(255),
    commands VARCHAR(255),
    birth_date DATE,
    FOREIGN KEY (id_animal) REFERENCES HomePets(id)
);

SELECT * FROM Dogs;

CREATE TABLE IF NOT EXISTS Cats (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_animal INT,
    name_animal VARCHAR(255),
    commands VARCHAR(255),
    birth_date DATE,
    FOREIGN KEY (id_animal) REFERENCES HomePets(id)
);

SELECT * FROM Cats;

CREATE TABLE IF NOT EXISTS Hamsters (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_animal INT,
    name_animal VARCHAR(255),
    commands VARCHAR(255),
    birth_date DATE,
    FOREIGN KEY (id_animal) REFERENCES HomePets(id)
);

SELECT * FROM Hamsters;

CREATE TABLE IF NOT EXISTS Horses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_animal INT,
    name_animal VARCHAR(255),
    commands VARCHAR(255),
    birth_date DATE,
    FOREIGN KEY (id_animal) REFERENCES PackAnimals(id)
);

CREATE TABLE IF NOT EXISTS Camels (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_animal INT,
    name_animal VARCHAR(255),
    commands VARCHAR(255),
    birth_date DATE,
    FOREIGN KEY (id_animal) REFERENCES PackAnimals(id)
);

CREATE TABLE IF NOT EXISTS Donkeys (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_animal INT,
    name_animal VARCHAR(255),
    commands VARCHAR(255),
    birth_date DATE,
    FOREIGN KEY (id_animal) REFERENCES PackAnimals(id)
);

INSERT INTO HomePets (name_animal, type_animal, birth_date) VALUES ('Барон', 'Собака', '2020-05-15');
INSERT INTO HomePets (name_animal, type_animal, birth_date) VALUES ('Мурка', 'Кошка', '2021-02-20');
INSERT INTO HomePets (name_animal, type_animal, birth_date) VALUES ('Чарли', 'Хомяк', '2019-10-10');

INSERT INTO PackAnimals (name_animal, type_animal, birth_date) VALUES ('Гроза', 'Лошадь', '2018-08-05');
INSERT INTO PackAnimals (name_animal, type_animal, birth_date) VALUES ('Мага', 'Верблюд', '2017-04-12');
INSERT INTO PackAnimals (name_animal, type_animal, birth_date) VALUES ('Оскар', 'Осел', '2016-11-25');

SET @last_id = LAST_INSERT_ID();

INSERT INTO Dogs (id_animal, name_animal, commands, birth_date) VALUES (@last_id, 'Барон', 'Сидеть, Лежать', '2020-05-15');
INSERT INTO Cats (id_animal, name_animal, commands, birth_date) VALUES (@last_id, 'Мурка', 'Ловить мышей', '2021-02-20');
INSERT INTO Hamsters (id_animal, name_animal, commands, birth_date) VALUES (@last_id, 'Чарли', 'Крутиться в колесе', '2019-10-10');

INSERT INTO Horses (id_animal, name_animal, commands, birth_date) VALUES (@last_id, 'Гроза', 'Тянуть плуг', '2017-04-12');
INSERT INTO Camels (id_animal, name_animal, commands, birth_date) VALUES (@last_id, 'Мага', 'Нести грузы', '2017-04-12');
INSERT INTO Donkeys (id_animal, name_animal, commands, birth_date) VALUES (@last_idcamels, 'Оскар', 'Перевозить грузы', '2016-11-25');

SET SQL_SAFE_UPDATES = 0;
DELETE FROM Camels WHERE name_animal = 'Мага';

DROP TABLE IF EXISTS HorsesAndDonkeys;
CREATE TABLE IF NOT EXISTS HorsesAndDonkeys AS
SELECT id_animal, name_animal, 'Лошадь' AS type_animal, commands, birth_date
FROM Horses
UNION
SELECT id_animal, name_animal, 'Осел' AS type_animal, commands, birth_date
FROM Donkeys;

SELECT * FROM HorsesAndDonkeys;

DROP TABLE IF EXISTS YoungAnimals;
CREATE TABLE IF NOT EXISTS YoungAnimals AS
SELECT id, name_animal, type_animal, birth_date,
       TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) AS age_years,
       TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) % 12 AS age_months
FROM HomePets
WHERE TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) BETWEEN 1 AND 3;

SELECT * FROM YoungAnimals;

DROP TABLE IF EXISTS AllAnimals;
CREATE TABLE IF NOT EXISTS AllAnimals AS
SELECT
    'HomePets' AS source_table,
    id,
    name_animal,
    type_animal,
    birth_date,
    NULL AS commands,
    NULL AS age_years,
    NULL AS age_months
FROM HomePets
UNION
SELECT
    'PackAnimals',
    id,
    name_animal,
    type_animal,
    birth_date,
    NULL,
    NULL,
    NULL
FROM PackAnimals
UNION
SELECT
    'Dogs',
    id_animal,
    name_animal,
    NULL,
    birth_date,
    commands,
    NULL,
    NULL
FROM Dogs
UNION
SELECT
    'Cats',
    id_animal,
    name_animal,
    NULL,
    birth_date,
    commands,
    NULL,
    NULL
FROM Cats
UNION
SELECT
    'Hamsters',
    id_animal,
    name_animal,
    NULL,
    birth_date,
    commands,
    NULL,
    NULL
FROM Hamsters
UNION
SELECT
    'HorsesAndDonkeys',
    id_animal,
    name_animal,
    type_animal,
    birth_date,
    commands,
    NULL,
    NULL
FROM HorsesAndDonkeys
UNION
SELECT
    'YoungAnimals',
    id,
    name_animal,
    type_animal,
    birth_date,
    NULL AS commands,
    age_years,
    age_months
FROM YoungAnimals;

SELECT * FROM AllAnimals;