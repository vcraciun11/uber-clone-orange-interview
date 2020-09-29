CREATE TABLE cars
(
    id                     uuid primary key,
    name                   varchar(150) not null,
    color                  varchar(100) not null,
    registration_number    varchar(10)  not null,
    registration_timestamp timestamp    not null,
    status                 varchar(20)  not null
);