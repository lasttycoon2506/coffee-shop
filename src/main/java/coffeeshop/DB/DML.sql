SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT = 0;
SET sql_mode = 'STRICT_ALL_TABLES';


/* Insertion of Data into Tables */
INSERT INTO Customers (user_name, pword, first_name, last_name, email, phone) 
VALUES (:userName, :pword, :firstName, :lastName, :email, :phone);

INSERT INTO Coffee (brand, roast, price, coffee_size) 
VALUES (:brand, :roast, :price, :coffeeSize);

INSERT INTO Orders (order_date, total_items, customer_id) 
VALUES (:orderDate, :totalItems, :customerId);

INSERT INTO Items (coffee_id, quantity, order_id) 
VALUES (:coffeeId, :quantity, :orderId);

/* SQL & corresponding JPA query */
SELECT user_name FROM Customers WHERE user_name LIKE :userName;
SELECT user FROM Customer user WHERE user.userName = :userName

SELECT email FROM Customers WHERE email LIKE :email;
SELECT email FROM Customer email WHERE email.email = :email

SELECT phone FROM Customers WHERE phone LIKE :phone;
SELECT phone FROM Customer phone WHERE phone.phone = :phone

SELECT pword FROM Customers WHERE user_name LIKE :userName
SELECT pwHash FROM Customer pwHash WHERE userName = :userName


SET FOREIGN_KEY_CHECKS=1;
COMMIT;


