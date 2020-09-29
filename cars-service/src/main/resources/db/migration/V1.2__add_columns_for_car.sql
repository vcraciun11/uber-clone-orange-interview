alter table cars
    add column driver_id uuid not null;
alter table cars
    add constraint registration_number_unique_contraint UNIQUE (registration_number);
alter table cars
    alter column registration_timestamp set default now();

