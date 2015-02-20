CREATE SCHEMA IF NOT EXISTS sparkle;

CREATE TABLE sparkle.brands
(
  id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(25)
);

CREATE TABLE sparkle.families
(
  id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  id_brand integer NOT NULL REFERENCES brands(id),
  symbol VARCHAR(100) UNIQUE,
  name VARCHAR(100)
);

CREATE TABLE sparkle.subfamilies
(
  id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  id_family integer NOT NULL REFERENCES families(id),
  symbol VARCHAR(100) UNIQUE,
  name VARCHAR(100)
);

CREATE TABLE sparkle.gender
(
  id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(25)
);

CREATE TABLE sparkle.labels
(
  id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(15)
);

CREATE TABLE sparkle.colours
(
  id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(15)
);

CREATE TABLE sparkle.products
(
  ref_num character varying(30) NOT NULL PRIMARY KEY,
  id_subfamily integer NOT NULL REFERENCES sparkle.subfamilies(id),
  name character varying(70),
  description character varying(1000),
  price real,
  id_label integer REFERENCES sparkle.labels(id),
  id_gender integer REFERENCES sparkle.gender(id),
  id_colour integer REFERENCES sparkle.colours(id),
  CHECK (price > 0)
);
