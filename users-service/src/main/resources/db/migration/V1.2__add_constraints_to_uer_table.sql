ALTER TABLE users
    ALTER COLUMN first_name SET NOT NULL;
ALTER TABLE users
    ALTER COLUMN last_name SET NOT NULL;
ALTER TABLE users
    ALTER COLUMN phone_number SET NOT NULL;
ALTER TABLE users
    ADD CONSTRAINT unique_phone_number UNIQUE (phone_number);