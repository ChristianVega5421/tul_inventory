CREATE TABLE inventory.product
(
    sku uuid NOT NULL,
    name character varying(255) NOT NULL,
    description text NOT NULL,
    price numeric NOT NULL,
    stock bigint NOT NULL,
    reserved bigint,
    PRIMARY KEY (sku)
);

ALTER TABLE inventory.product
    OWNER to postgres;