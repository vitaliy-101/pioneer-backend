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


create table if not exists product
(
    id                          BIGSERIAL,
    operating_temperature       VARCHAR,
    degree_protection           VARCHAR,
    battery_life                INT,
    warning_alarm               BOOLEAN,
    display                     BOOLEAN,
    calibration                 VARCHAR,
    response_time               INT,
    explosion_protection_rating VARCHAR,
    power_supply                VARCHAR,
    country                     VARCHAR,
    operating_humidity          VARCHAR,
    manufacturer                VARCHAR,
    sensor_type                 VARCHAR,
    gas_sampling                VARCHAR,
    enclosure                   VARCHAR,
    operating_time              INT,
    charging_time               INT,
    description                 VARCHAR,
    title                       VARCHAR,
    price                       REAL,
    measured_gases_type         VARCHAR,
    channel_type                VARCHAR,
    type                        VARCHAR,
    wireless_link               VARCHAR,
    integrated_pump             VARCHAR,
    additional                  TEXT[],
    combustible_gases           VARCHAR,
    oxygen                      VARCHAR,
    carbon_monoxide             VARCHAR,
    hydrogen_sulphide           VARCHAR,
    created_at                  TIMESTAMP,
    sales                       INT,
    CONSTRAINT pk_product primary key (id)
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