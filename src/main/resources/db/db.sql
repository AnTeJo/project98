ALTER TABLE table_name DROP COLUMN column_name
select * from payments
SELECT column_name, data_type
FROM information_schema.columns
WHERE table_name = 'payments';
ALTER TABLE payments ALTER COLUMN id TYPE BIGINT;
CREATE SEQUENCE payments_id_seq;
SELECT setval('payments_id_seq', (SELECT MAX(id) FROM payments));
ALTER TABLE payments ALTER COLUMN id SET DEFAULT nextval('payments_id_seq');
SELECT * FROM pg_sequences WHERE sequencename = 'payments_id_seq';
DROP SEQUENCE IF EXISTS payments_id_seq CASCADE;
CREATE SEQUENCE payments_id_seq;
ALTER TABLE payments ALTER COLUMN id SET DEFAULT nextval('payments_id_seq');
DELETE FROM payments;
insert into
drop table payments;

CREATE SEQUENCE payments_id_seq;
SELECT EXISTS(SELECT 1 FROM pg_sequences WHERE sequencename='payments_id_seq');

CREATE TABLE payments (
    id_payment BIGINT PRIMARY KEY,
    card_number VARCHAR(16) NOT NULL,
    card_holder_name VARCHAR(255) NOT NULL,
    exp_month VARCHAR(10) NOT NULL,
    exp_year VARCHAR(10) NOT NULL,
    card_cvv VARCHAR(4) NOT NULL,
    sum_payment NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP DEFAULT NOW()
);
INSERT INTO payments (card_number, card_holder_name, expiration_date, card_cvv, sum_payment)
VALUES ('1234567890123456', 'John Smith', '12/23', '123', 100.50);
ALTER SEQUENCE payments_id_seq RESTART WITH 1;
ALTER TABLE payments ALTER COLUMN id_payment SET DEFAULT nextval('payments_id_seq');
