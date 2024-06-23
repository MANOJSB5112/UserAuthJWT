CREATE TABLE roles
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    deleted BIT(1) NULL DEFAULT false,
    name    VARCHAR(255) NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE tokens
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    deleted   BIT(1) NULL DEFAULT false,
    user_id   BIGINT NULL,
    value     VARCHAR(255) NULL,
    expiry_at datetime NULL,
    CONSTRAINT pk_tokens PRIMARY KEY (id)
);

CREATE TABLE user
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    deleted         BIT(1) NULL DEFAULT false,
    name            VARCHAR(255) NULL,
    email           VARCHAR(255) NULL,
    hashed_password VARCHAR(255) NULL,
    is_verified     BIT(1) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL
);

ALTER TABLE tokens
    ADD CONSTRAINT FK_TOKENS_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_roles FOREIGN KEY (roles_id) REFERENCES roles (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);