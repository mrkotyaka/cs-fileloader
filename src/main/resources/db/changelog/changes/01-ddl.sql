--liquibase formatted sql
--changeset mrkotyaka:1
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(50) UNIQUE NOT NULL,
    password   VARCHAR(100)       NOT NULL
);

CREATE TABLE files
(
    filename          VARCHAR(255) NOT NULL,
    original_filename VARCHAR(255) NOT NULL,
    size              BIGINT       NOT NULL,
    content_type      VARCHAR(100),
    user_id           BIGINT       NOT NULL,
    file_data         BYTEA,
    CONSTRAINT fk_files_user_id
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE,
    CONSTRAINT unique_filename_per_user
        UNIQUE (filename, user_id)
);

CREATE TABLE tokens
(
    token      VARCHAR(255) PRIMARY KEY,
    user_id    BIGINT              NOT NULL,
    is_active  BOOLEAN   DEFAULT TRUE,
    CONSTRAINT fk_tokens_user_id
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
);
--rollback DROP TABLE users;
--rollback DROP TABLE authorities;
--rollback DROP TABLE files;
--rollback DROP TABLE auth_tokens;