
CREATE DATABASE IF NOT EXISTS health_hub;
USE health_hub;


CREATE TABLE users (
                       id CHAR(36) PRIMARY KEY NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE appointments (
                              id CHAR(36) PRIMARY KEY NOT NULL,
                              date_time DATETIME NOT NULL,
                              location VARCHAR(255) NOT NULL,
                              notes TEXT NOT NULL,
                              user_id CHAR(36),
                              FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE medications (
                             id CHAR(36) PRIMARY KEY NOT NULL,
                             name VARCHAR(100) NOT NULL,
                             dosage VARCHAR(50) NOT NULL,
                             refill_schedule VARCHAR(100),
                             user_id CHAR(36),
                             FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE providers (
                           id CHAR(36) PRIMARY KEY NOT NULL,
                           name VARCHAR(100) NOT NULL,
                           specialty VARCHAR(100),
                           contact_info VARCHAR(255),
                           user_id CHAR(36),
                           FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE health_log (
                            id CHAR(36) PRIMARY KEY NOT NULL,
                            ailment VARCHAR(255) NOT NULL,
                            symptoms TEXT,
                            family_history TEXT,
                            user_id CHAR(36),
                            FOREIGN KEY (user_id) REFERENCES users(id)
);
