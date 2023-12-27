SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT = 0;
SET sql_mode = 'STRICT_ALL_TABLES';


CREATE TABLE IF NOT EXISTS Customers
(
    customer_id INT NOT NULL AUTO_INCREMENT UNIQUE,
    user_name VARCHAR(50) NOT NULL UNIQUE,
    pword VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone  VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS Orders
(
   orders_id INT NOT NULL AUTO_INCREMENT UNIQUE,
   orders_date DATE NOT NULL,
   number_of_total_items INT NOT NULL,
   customer_id INT NOT NULL,
   PRIMARY KEY(orders_id),
   CONSTRAINT FK_Orders_Customer_Id
   FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
);

CREATE TABLE IF NOT EXISTS Coffee
(
   coffee_id INT NOT NULL AUTO_INCREMENT UNIQUE,
   brand VARCHAR(50) NOT NULL,
   coffee_name VARCHAR(50) NOT NULL,
   roast VARCHAR(50) NOT NULL,
   price VARCHAR(50) NOT NULL,
   region VARCHAR(50) NOT NULL,
   coffee_size INT NOT NULL,
   PRIMARY KEY(coffee_id)
);

-- /*Intersection Table */ 
CREATE TABLE IF NOT EXISTS Items
(
   items_id INT NOT NULL AUTO_INCREMENT UNIQUE,
   quantity INT NOT NULL,
   coffee_id INT, 
   orders_id INT NOT NULL,
   PRIMARY KEY(items_id),
   CONSTRAINT FK_Items_Coffee_Id
   FOREIGN KEY (coffee_id) REFERENCES Coffee(coffee_id),
   CONSTRAINT FK_Orders_Order_Id
   FOREIGN KEY (orders_id) REFERENCES Orders(orders_id) ON DELETE CASCADE
);


SET FOREIGN_KEY_CHECKS=1;
COMMIT;

