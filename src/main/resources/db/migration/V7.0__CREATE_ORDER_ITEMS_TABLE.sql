CREATE TABLE TBL_ORDER_ITEMS
(
    ID         BIGINT      NOT NULL,
    AMOUNT     BIGINT      NOT NULL,
    PRODUCT_ID BIGINT,
    ORDER_ID   BIGINT      NOT NULL,
    UUID       VARCHAR(36) NOT NULL,
    INC_DATE   TIMESTAMP   NOT NULL,
    MODF_DATE  TIMESTAMP,
    PRIMARY KEY (ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES TBL_PRODUCT (ID),
    FOREIGN KEY (ORDER_ID) REFERENCES TBL_ORDER (ID)
);

CREATE SEQUENCE SQ_ORDER_ITEMS START 1;
