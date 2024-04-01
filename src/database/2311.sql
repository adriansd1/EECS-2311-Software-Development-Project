--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Employees; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Employees" (
    "Name" character varying,
    "Role" character varying,
    "Wage" double precision
);


ALTER TABLE public."Employees" OWNER TO postgres;

--
-- Name: Orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Orders" (
    "Food name" character varying(255),
    "Price" double precision,
    "Quantity" integer,
    "IsCompleted" boolean,
    "TableNumber" integer
);


ALTER TABLE public."Orders" OWNER TO postgres;

--
-- Name: PaymentHistory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."PaymentHistory" (
    "TableNumber" integer,
    "TotalBeforeTip" double precision,
    "TipAmount" double precision,
    "TotalAfterTip" double precision
);


ALTER TABLE public."PaymentHistory" OWNER TO postgres;

--
-- Name: ShiftHistory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."ShiftHistory" (
    employee_name character varying,
    shift_length character varying
);


ALTER TABLE public."ShiftHistory" OWNER TO postgres;

--
-- Name: Tables; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Tables" (
    "TableNumber" integer,
    "IsAYCE" boolean
);


ALTER TABLE public."Tables" OWNER TO postgres;

--
-- PostgreSQL database dump complete
--

