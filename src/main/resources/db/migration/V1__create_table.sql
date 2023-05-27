CREATE TABLE IF NOT EXISTS produto
(
    id uuid NOT NULL,
    descricao text COLLATE pg_catalog."default",
    disponivel boolean,
    nome character varying(255) COLLATE pg_catalog."default",
    valor real,
    CONSTRAINT produto_pkey PRIMARY KEY (id)
);