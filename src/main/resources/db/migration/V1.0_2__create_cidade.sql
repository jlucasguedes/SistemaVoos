-- Table: public.cidade

-- DROP TABLE IF EXISTS public.cidade;

CREATE TABLE IF NOT EXISTS public.cidade
(
    id bigint NOT NULL,
    nome character varying(255) COLLATE pg_catalog."default",
    estado_id bigint,
    CONSTRAINT cidade_pkey PRIMARY KEY (id),
    CONSTRAINT fkkworrwk40xj58kevvh3evi500 FOREIGN KEY (estado_id)
        REFERENCES public.estado (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cidade
    OWNER to postgres;