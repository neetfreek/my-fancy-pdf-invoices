CREATE TABLE if NOT EXISTS invoices
(
    id      uuid  DEFAULT random_uuid() PRIMARY KEY ,
    pdf_url VARCHAR (255),
    user_id VARCHAR(255),
    amount  INT
    );