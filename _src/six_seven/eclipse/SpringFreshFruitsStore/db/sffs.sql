SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

-- CREATE PROCEDURAL LANGUAGE plpgsql;
-- ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;
SET default_tablespace = '';
SET default_with_oids = false;

CREATE TABLE authentication (
    username character varying NOT NULL,
    password character varying,
    enabled boolean DEFAULT false,
    id integer NOT NULL
);
-- user:admin pass:admin, user:max pass:max 
INSERT INTO authentication (username, password, enabled, id) VALUES ('admin', '0706025b2bbcec1ed8d64822f4eccd96314938d0', true, 1);
INSERT INTO authentication (username, password, enabled, id) VALUES ('max', '0706025b2bbcec1ed8d64822f4eccd96314938d0', true, 2);

CREATE TABLE roles (
    id integer NOT NULL,
    rolename character varying NOT NULL,
    username character varying NOT NULL
);

INSERT INTO roles (rolename, username, id) VALUES ('ROLE_ADMIN', 'admin', 1);
INSERT INTO roles (rolename, username, id) VALUES ('ROLE_USER', 'max', 2);

CREATE TABLE customers (
    id bigint NOT NULL,
    name character varying NOT NULL,
    street character varying,
    city character varying,
    state character varying,
    email character varying,
    fax character varying,
    mobile character varying,
    phone character varying,
    enabled boolean DEFAULT true
);

ALTER TABLE public.customers OWNER TO sffs;

CREATE TABLE fruittype (
    id integer NOT NULL,
    flavour character varying,
    color character varying,
    location character varying NOT NULL,
    name character varying NOT NULL,
    price double precision NOT NULL
);

ALTER TABLE public.fruittype OWNER TO sffs;

COMMENT ON TABLE fruittype IS 'Table with various types of fruit';

CREATE TABLE orderitems (
    id_order bigint NOT NULL,
    id_fruit bigint NOT NULL,
    quantity integer DEFAULT 0 NOT NULL
);

ALTER TABLE public.orderitems OWNER TO sffs;

CREATE TABLE orders (
    id bigint NOT NULL,
    date date NOT NULL,
    id_customer bigint NOT NULL
);

ALTER TABLE public.orders OWNER TO sffs;

CREATE SEQUENCE customers_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
    CYCLE;

ALTER TABLE public.customers_id_seq OWNER TO sffs;

SELECT pg_catalog.setval('customers_id_seq', 2080, true);

CREATE SEQUENCE fruittype_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
    CYCLE;

ALTER TABLE public.fruittype_id_seq OWNER TO sffs;

SELECT pg_catalog.setval('fruittype_id_seq', 2, true);

CREATE SEQUENCE orders_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
    CYCLE;

ALTER TABLE public.orders_id_seq OWNER TO sffs;

SELECT pg_catalog.setval('orders_id_seq', 1537, true);

ALTER TABLE ONLY customers
    ADD CONSTRAINT customers_pk PRIMARY KEY (id);

ALTER TABLE ONLY fruittype
    ADD CONSTRAINT fruittype_pk PRIMARY KEY (id);

ALTER TABLE ONLY orderitems
    ADD CONSTRAINT orderitems_pk PRIMARY KEY (id_order, id_fruit);

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT customers_fk FOREIGN KEY (id_customer) REFERENCES customers(id);

ALTER TABLE ONLY orderitems
    ADD CONSTRAINT orders_fk FOREIGN KEY (id_order) REFERENCES orders(id);

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;