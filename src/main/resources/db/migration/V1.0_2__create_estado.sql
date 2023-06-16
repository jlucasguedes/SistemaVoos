-- Table: public.estado

-- DROP TABLE IF EXISTS public.estado;

CREATE TABLE IF NOT EXISTS public.estado
(
    id bigint NOT NULL,
    nome character varying(255) COLLATE pg_catalog."default",
    uf character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT estado_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.estado
    OWNER to postgres;