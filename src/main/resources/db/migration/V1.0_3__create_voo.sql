-- Table: public.voo

-- DROP TABLE IF EXISTS public.voo;

CREATE TABLE IF NOT EXISTS public.voo
(
    id bigint NOT NULL,
    data_partida date NOT NULL,
    preco numeric(38,2) NOT NULL,
    destino_id bigint NOT NULL,
    origem_id bigint NOT NULL,
    CONSTRAINT voo_pkey PRIMARY KEY (id),
    CONSTRAINT fk53rk0td7ims5e2nswj4lygela FOREIGN KEY (destino_id)
        REFERENCES public.cidade (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fklsi9wm8jnb0ofylesvbovgbfi FOREIGN KEY (origem_id)
        REFERENCES public.cidade (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.voo
    OWNER to postgres;