create table payments
(
    id                uuid primary key,
    amount            decimal      not null,
    initiation_time   timestamp    not null,
    confirmation_time timestamp,
    payment_type      varchar(100) not null,
    payment_status    varchar(100) not null,
    trip_id           uuid         not null,
    driver_id         uuid         not null
);