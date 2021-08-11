CREATE TABLE idus_member
(
    id              INT          NOT NULL AUTO_INCREMENT,
    password        VARCHAR(256) not NULL,
    gender          VARCHAR(45),
    name            VARCHAR(21)  not null,
    alias           VARCHAR(30)  not NULL,
    email           VARCHAR(101) not NULL,
    phone           VARCHAR(21)  not NULL,
    role            VARCHAR(45),
    last_login_date timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE idus_order
(
    id                 INT          NOT NULL AUTO_INCREMENT,
    order_key          VARCHAR(13)  NOT NULL,
    orderer_id         INT          NOT NULL,
    status             VARCHAR(45)  NOT NULL,
    created_by         VARCHAR(255) NULL DEFAULT 'null',
    created_date       DATETIME(6)  NULL DEFAULT NULL,
    last_modified_by   VARCHAR(255) NULL DEFAULT 'null',
    last_modified_date DATETIME(6)  NULL DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_member_order
        FOREIGN KEY (orderer_id)
            REFERENCES idus_member (id)
);

CREATE TABLE idus_order_detail
(
    id                 INT            NOT NULL AUTO_INCREMENT,
    order_id           INT            NOT NULL,
    amount             decimal(19, 2) NOT NULL,
    created_by         VARCHAR(255)   NULL DEFAULT 'null',
    created_date       DATETIME(6)    NULL DEFAULT NULL,
    last_modified_by   VARCHAR(255)   NULL DEFAULT 'null',
    last_modified_date DATETIME(6)    NULL DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT idus_order_order_detail
        FOREIGN KEY (order_id)
            REFERENCES idus_order (id)
);

CREATE TABLE idus_ordered_product
(
    id           INT            NOT NULL AUTO_INCREMENT,
    order_id     INT            NOT NULL,
    product_id   INT            NOT NULL,
    product_name VARCHAR(255)   NOT NULL,
    sale_price   decimal(19, 2) NOT NULL,
    quantity     int(11),
    PRIMARY KEY (id),
    CONSTRAINT idus_order_ordered_product
        FOREIGN KEY (order_id)
            REFERENCES idus_order (id)
);
