CREATE TABLE trips
(
    id             uuid PRIMARY KEY,
    amount         decimal   not null,
    start_location text      NOT NULL,
    end_location   text      NOT NULL,
    start_time     timestamp NOT NULL,
    end_time       timestamp,
    rating         integer,
    driver_id      uuid
);