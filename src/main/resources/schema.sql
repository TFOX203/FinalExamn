-- Script de base de datos - Concesionario AUDI
-- @author Alexander

CREATE TABLE IF NOT EXISTS users (
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    email        VARCHAR(150) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    role         VARCHAR(20)  NOT NULL DEFAULT 'USER',
    balance      FLOAT,
    dni          BIGINT,
    age          INTEGER,
    tlf          BIGINT,
    address      VARCHAR(255),
    license_type VARCHAR(10),
    nationality  VARCHAR(80)
);

CREATE TABLE IF NOT EXISTS products (
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(200) NOT NULL UNIQUE,
    description  TEXT,
    price        DOUBLE PRECISION NOT NULL,
    stock        INTEGER NOT NULL DEFAULT 0,
    category     VARCHAR(100) NOT NULL,
    year         INTEGER,
    horsepower   INTEGER,
    transmission VARCHAR(30),
    color        VARCHAR(50)
);




