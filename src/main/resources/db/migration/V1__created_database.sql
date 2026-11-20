create table appointments
(
    id        binary(16)   not null primary key,
    date_time datetime     not null,
    location  varchar(255) not null,
    notes     TEXT         not null
);

create table health_log
(
    id             binary(16)   not null primary key,
    `condition`    varchar(255) not null,
    symptoms       TEXT         not null,
    family_history text         not null
);

create table medications
(
    id              binary(16)   not null primary key,
    name            varchar(100) not null,
    dosage          varchar(50)  not null,
    refill_schedule varchar(100) not null,
    instructions    text         not null
);

create table providers
(
    id           binary(16)   not null primary key,
    name         varchar(100) not null,
    specialty    varchar(100) not null,
    contact_info varchar(255) not null
);

create table users
(
    id    binary(16)   not null primary key,
    name  varchar(100) not null,
    email varchar(255) not null unique
);