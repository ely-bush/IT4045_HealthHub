-- ==========================================
-- Health Hub Initial Schema
-- ==========================================

CREATE TABLE users (
                       id BINARY(16) PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       -- constraint to ensure emails are unique
                       CONSTRAINT uk_users_email UNIQUE (email)
);

CREATE TABLE appointments (
                              id BINARY(16) PRIMARY KEY,
                              date_time TIMESTAMP NOT NULL,
                              location VARCHAR(255) NOT NULL,
                              notes TEXT NOT NULL,
                              user_id BINARY(16) NOT NULL,
                              -- constraint to ensure appointments are linked to existing users
                              CONSTRAINT fk_appointments_user
                                  FOREIGN KEY (user_id)
                                      REFERENCES users(id)
);

CREATE TABLE medications (
                             id BINARY(16) PRIMARY KEY,
                             name VARCHAR(100) NOT NULL,
                             dosage VARCHAR(50) NOT NULL,
                             refill_schedule VARCHAR(100),
                             instructions TEXT,
                             user_id BINARY(16) NOT NULL,
                             -- constraint to ensure medications are linked to existing users
                             CONSTRAINT fk_medications_user
                                 FOREIGN KEY (user_id)
                                     REFERENCES users(id)
);

CREATE TABLE providers (
                           id BINARY(16) PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           specialty VARCHAR(100),
                           contact_info VARCHAR(255),
                           user_id BINARY(16) NOT NULL,
                            -- constraint to ensure providers are linked to existing users
                           CONSTRAINT fk_providers_user
                               FOREIGN KEY (user_id)
                                   REFERENCES users(id)
);

CREATE TABLE health_log (
                            id BINARY(16) PRIMARY KEY,
                            condition_name VARCHAR(255) NOT NULL,
                            symptoms TEXT,
                            family_history TEXT,
                            user_id BINARY(16) NOT NULL,
                            -- constraint to ensure health logs are linked to existing users
                            CONSTRAINT fk_health_log_user
                                FOREIGN KEY (user_id)
                                    REFERENCES users(id)
);

CREATE INDEX idx_appointments_user_id
    ON appointments(user_id);

CREATE INDEX idx_medications_user_id
    ON medications(user_id);

CREATE INDEX idx_providers_user_id
    ON providers(user_id);

CREATE INDEX idx_health_log_user_id
    ON health_log(user_id);