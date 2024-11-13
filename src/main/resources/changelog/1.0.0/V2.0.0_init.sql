--DROP SCHEMA IF EXISTS general CASCADE;

CREATE SCHEMA IF NOT EXISTS general;

--UTILS SCHEMA

CREATE TABLE general.warehouse
(
    id varchar    NOT NULL,
    is_exist_in_warehouse boolean  NOT NULL        DEFAULT FALSE,
    name varchar NOT NULL,
    CONSTRAINT pk_warehouse
        PRIMARY KEY (id)
);


