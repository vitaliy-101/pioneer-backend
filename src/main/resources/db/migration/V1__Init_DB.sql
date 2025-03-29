create table if not exists users
(
    id       BIGSERIAL,
    email    VARCHAR,
    password VARCHAR,
    role     VARCHAR,
    CONSTRAINT pk_users primary key (id)
);

create table if not exists tokens
(
    id           BIGSERIAL,
    access_token VARCHAR,
    logged_out   BOOLEAN,
    user_id      BIGINT,
    CONSTRAINT pk_tokens primary key (id),
    CONSTRAINT fk_token_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

create table if not exists manufacturer
(
    id      BIGSERIAL,
    name    VARCHAR,
    country VARCHAR,
    image   bytea,
    CONSTRAINT pk_manufacturer primary key (id)
);

create table if not exists product
(
    id                          BIGSERIAL,
    operating_temperature       VARCHAR,
    degree_protection           VARCHAR,
    warning_alarm               VARCHAR,
    display                     VARCHAR,
    calibration                 VARCHAR,
    response_time               VARCHAR,
    explosion_protection_rating VARCHAR,
    power_supply                VARCHAR,
    operating_humidity          VARCHAR,
    sensor_type                 VARCHAR,
    gas_sampling                VARCHAR,
    enclosure                   VARCHAR,
    operating_time              VARCHAR,
    charging_time               VARCHAR,
    description                 VARCHAR,
    title                       VARCHAR,
    price                       REAL,
    channel_type                VARCHAR,
    type                        VARCHAR,
    additional                  TEXT[],
    measuring_ranges            TEXT[],
    created_at                  TIMESTAMP,
    sales                       INT,
    manufacturer_id             BIGINT,
    CONSTRAINT pk_product primary key (id),
    CONSTRAINT fk_manufacturer_id FOREIGN KEY (manufacturer_id) REFERENCES manufacturer (id) ON DELETE SET NULL
);


create table if not exists product_image
(
    id         BIGSERIAL,
    image      bytea,
    is_main    BOOLEAN,
    product_id BIGINT,
    CONSTRAINT pk_product_photo primary key (id),
    CONSTRAINT fk_product_photo FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);


create table if not exists basket
(
    id         BIGSERIAL,
    count      INT,
    created_at TIMESTAMP,
    uuid       VARCHAR,
    user_id    BIGINT,
    product_id BIGINT,
    CONSTRAINT pk_bucket primary key (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);

create table if not exists docs
(
    id         BIGSERIAL,
    name       VARCHAR,
    data       bytea,
    type       VARCHAR,
    product_id BIGINT,
    CONSTRAINT pk_docs primary key (id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);


create table if not exists filter
(
    id   BIGSERIAL,
    data TEXT[],
    CONSTRAINT pk_filter primary key (id)
);
