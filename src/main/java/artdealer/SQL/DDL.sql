SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT = 0;
SET sql_mode = 'STRICT_ALL_TABLES';


CREATE OR REPLACE TABLE Customers
(
    customer_id INT NOT NULL AUTO_INCREMENT UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone  VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (customer_id)
);

CREATE OR REPLACE TABLE Employees
(
    employee_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email  VARCHAR(50) NOT NULL UNIQUE,
    phone  VARCHAR(50) NOT NULL UNIQUE, 
    title  VARCHAR(50) NOT NULL,
    PRIMARY KEY (employee_id)
);

CREATE OR REPLACE TABLE Coffee
(
    coffee_id INT AUTO_INCREMENT NOT NULL UNIQUE,
    brand VARCHAR(50) NOT NULL,
    coffee_name  VARCHAR(50) NOT NULL,
    roast_type VARCHAR(50) NOT NULL,
    price  DECIMAL(13,2) NOT NULL, 
    region VARCHAR(50) NOT NULL,
    coffee_size int NOT NULL,
    PRIMARY KEY (id)
);

CREATE OR REPLACE TABLE Orders
(
   orders_id int NOT NULL AUTO_INCREMENT UNIQUE,
   orders_date date NOT NULL,
   customer_id int NOT NULL,
   employee_id int NOT NULL,
   order_status BOOLEAN NOT NULL DEFAULT FALSE,
   PRIMARY KEY(orders_id),
   CONSTRAINT FK_Orders_Customer_Id
   FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
   CONSTRAINT FK_Orders_Employee_Id
   FOREIGN KEY (employee_id) REFERENCES Employees(employee_id) 
);

/*Intersection Table */ 

CREATE OR REPLACE TABLE Items
(
   items_id int NOT NULL AUTO_INCREMENT UNIQUE,
   quantity int NOT NULL,
   coffee_id int, 
   orders_id int NOT NULL,
   PRIMARY KEY(items_id),
   CONSTRAINT FK_Items_Coffee_Id
   FOREIGN KEY (coffee_id) REFERENCES Coffee(coffee_id),
   CONSTRAINT FK_Orders_Order_Id
   FOREIGN KEY (orders_id) REFERENCES Order(orders_id) ON DELETE CASCADE
);

/* Insertion of Data into Tables */

INSERT INTO Customers (first_name, last_name, email, phone) 
VALUES ('Chrissy', 'Rod', 'crod@crod.com', '263-643-4726'),
('Jimbo', 'Fisher', 'jfish@jfish.com', '265-783-6846'),
('Gibby', 'Loo', 'gibbloo@gloo.com', '583-376-8603')

INSERT INTO Employees (first_name, last_name, email, phone, title) 
VALUES ('Johnny', 'Appleseed', 'johnnyapp@zippity.com', '111-111-1111', 'The Man'),
('Weeping', 'Willow', 'mopey@gmail.com', '222-222-2222', 'Not the Man'),
('Ludwig', 'Van', 'thebest@besty.com', '123-456-7890', 'Worker')

INSERT INTO Coffee (brand, coffee_name, roast_type, price, region, coffee_size) 
VALUES ('Islandy', 'Island Chill', 'Medium', 14.99, 'Indonesia', 14),
('Kona', 'Wild Roast', 'Dark', 12.99, 'Ethiopia', 18),
('Billy`s', 'Buck Roast', 'Light', 13.99, 'Columbia', 16)

INSERT INTO Orders (orders_date, customer_id, employee_id, order_status) 
VALUES ('2023-08-05', 1, 1, FALSE),
('1975-5-5', 2, 2, FALSE)
('2020-3-17', 3, 3, FALSE)

/* Intersection Table */

INSERT INTO Items (quantity, coffee_id, orders_id) 
VALUES (3, 2, 1),
(2, 1, 2),
(1, NULL, 3),

SET FOREIGN_KEY_CHECKS=1;
COMMIT;
