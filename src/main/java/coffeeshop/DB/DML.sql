SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT = 0;
SET sql_mode = 'STRICT_ALL_TABLES';


/* Insertion of Data into Tables */
INSERT INTO Customers (user_name, pword, first_name, last_name, email, phone) 
VALUES (:user_name, :pword, :first_name, :last_name, :email, :phone);

INSERT INTO Employees (first_name, last_name, email, phone, title) 
VALUES (:first_name, :last_name, :email, :phone, :title);

INSERT INTO Coffee (brand, coffee_name, roast_type, price, region, coffee_size) 
VALUES (:brand, :coffee_name, :roast_type, :price, :region, :coffee_size);

INSERT INTO Orders (orders_date, customer_id, employee_id, order_status) 
VALUES (:orders_date, :customer_id, :employee_id, :order_status);

INSERT INTO Items (quantity, coffee_id, orders_id) 
VALUES (:quantity, :coffee_id, :orders_id);

/* SQL & corresponding JPA query */
SELECT user_name from Customers WHERE user_name LIKE :userName;
SELECT u from Customer u WHERE u.user_name = :userName

SELECT email from Customers WHERE email LIKE :email;
SELECT e from Customer e WHERE e.email = :email

SELECT phone from Customers WHERE phone LIKE :phone;
SELECT p from Customer p WHERE p.phone = :phone

SELECT pword from Customers WHERE user_name LIKE :userName
SELECT pwHash from Customer pwHash WHERE user_name = :userName


SET FOREIGN_KEY_CHECKS=1;
COMMIT;


