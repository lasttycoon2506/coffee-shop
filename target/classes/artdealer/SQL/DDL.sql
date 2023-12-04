SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT = 0;
SET sql_mode = 'STRICT_ALL_TABLES';


CREATE TABLE IF NOT EXISTS Customers
(
    customer_id INT NOT NULL AUTO_INCREMENT UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone  VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS Employees
(
    employee_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email  VARCHAR(50) NOT NULL UNIQUE,
    phone  VARCHAR(50) NOT NULL UNIQUE, 
    title  VARCHAR(50) NOT NULL,
    PRIMARY KEY (employee_id)
);

CREATE TABLE IF NOT EXISTS Coffee
(
    coffee_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    brand VARCHAR(50) NOT NULL,
    coffee_name  VARCHAR(50) NOT NULL,
    roast_type VARCHAR(50) NOT NULL,
    price  DECIMAL(13,2) NOT NULL, 
    region VARCHAR(50) NOT NULL,
    coffee_size int NOT NULL,
    PRIMARY KEY (coffee_id)
);

CREATE TABLE IF NOT EXISTS Orders
(
   orders_id INT NOT NULL AUTO_INCREMENT UNIQUE,
   orders_date DATE NOT NULL,
   customer_id INT NOT NULL,
   employee_id INT NOT NULL,
   order_status BOOLEAN NOT NULL DEFAULT FALSE,
   PRIMARY KEY(orders_id),
   CONSTRAINT FK_Orders_Customer_Id
   FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
   CONSTRAINT FK_Orders_Employee_Id
   FOREIGN KEY (employee_id) REFERENCES Employees(employee_id) 
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

