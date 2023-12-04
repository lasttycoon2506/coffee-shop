SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT = 0;
SET sql_mode = 'STRICT_ALL_TABLES';


/* Insertion of Data into Tables */
INSERT INTO Customers (customer_first_name, customer_last_name, customer_email, customer_phone, state, city, street, zip_code) 
VALUES (:customer_first_name, :customer_last_name, :customer_email, :customer_phone, :state, :city, :street, :zip);


