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

INSERT INTO Orders (orders_date, customer_id, employee_id, order_status) 
VALUES (:orders_date, :customer_id, :employee_id, :order_status);

INSERT INTO Items (quantity, coffee_id, orders_id) 
VALUES (:quantity, :coffee_id, :orders_id);

SET FOREIGN_KEY_CHECKS=1;
COMMIT;


