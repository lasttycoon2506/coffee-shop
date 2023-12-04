SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT = 0;
SET sql_mode = 'STRICT_ALL_TABLES';


/* Insertion of Data into Tables */
INSERT INTO Customers (first_name, last_name, email, phone) 
VALUES (:first_name, :last_name, :email, :phone);

INSERT INTO Employees (first_name, last_name, email, phone, title) 
VALUES (:first_name, :last_name, :email, :phone, :title);

INSERT INTO Coffee (brand, coffee_name, roast_type, price, region, coffee_size) 
VALUES (:brand, :coffee_name, :roast_type, :price, :region, :coffee_size);




