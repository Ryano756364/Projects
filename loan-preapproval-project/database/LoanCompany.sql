--Database: Ryan's Fake Loan Company
--None of this information is real
--see PNG file of ERD I took with pgAdmin

DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS loan;

-- Table that holds customer data
-- Data is fake
CREATE TABLE customer (
    customer_id serial,
    first_name varchar(128) NOT NULL,
    last_name varchar(128) NOT NULL,
    state char(2) NOT NULL,
    income_yearly INT NOT NULL,
    monthly_debts INT NOT NULL,
    credit_score INT NOT NULL,
    application_date varchar(20) NOT NULL,
    CONSTRAINT PK_customer PRIMARY KEY (customer_id)
);

-- Table to store what loans each customer qualifies for
-- Program, term, expiration may be null because a customer may not qualify for a loan
CREATE TABLE loan (
    loan_id serial,
    customer_id INT NOT NULL,
    program varchar(128),
    term_years INT,
    loan_amount INT NOT NULL,
    dti INT NOT NULL,
    expiration date,
    CONSTRAINT PK_loan PRIMARY KEY (loan_id),
    CONSTRAINT FK_loan_customer FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

INSERT INTO customer(first_name, last_name, state, income_yearly, monthly_debts, credit_score, application_date) VALUES
    ('Firstname1', 'LastName1', 'OH', 75000, 1800, 740, '2022-12-01'),
    ('Firstname2', 'LastName2', 'OH', 85000, 2000, 720, '2022-12-01'),
    ('Firstname3', 'LastName3', 'OH', 105000, 4000, 600, '2022-12-01'),
    ('Firstname4', 'LastName4', 'OH', 185000, 2000, 700, '2022-12-01'),
    ('Firstname5', 'LastName5', 'OH', 5000, 2000, 750, '2022-12-01');

INSERT INTO loan(customer_id, program, term_years, loan_amount, dti, expiration) VALUES
    (1, 'Conventional', 30, 400000, 40, '2023-03-01'),
    (1, 'FHA', 30, 400000, 40, '2023-03-01'),
    (2, 'Conventional', 30, 400000, 40, '2023-03-01'),
    (2, 'FHA', 30, 400000, 40, '2023-03-01'),
    (3, 'Conventional', 30, 400000, 40, '2023-03-01'),
    (3, 'FHA', 30, 400000, 40, '2023-03-01'),
    (4, 'Conventional', 30, 400000, 40, '2023-03-01'),
    (4, 'FHA', 30, 400000, 40, '2023-03-01'),
    (5, 'Conventional', 30, 400000, 40, '2023-03-01'),
    (5, 'FHA', 30, 400000, 40, '2023-03-01')