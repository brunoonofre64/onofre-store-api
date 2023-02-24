CREATE SEQUENCE IF NOT EXISTS SQ_USER_ROLES START 1;

CREATE TABLE TBL_USER_ROLES
(
    ID BIGINT NOT NULL DEFAULT NEXTVAL('SQ_USER_ROLES'),
    USER_ID BIGINT NOT NULL,
    ROLE_ID BIGINT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (USER_ID) REFERENCES TBL_USER (ID),
    FOREIGN KEY (ROLE_ID) REFERENCES TBL_ROLE (ID)
);