package coffeeshop.Entities.Coffee;


public enum Coffee{
    COFFEE1()
    COFFEE2()
    COFFEE1()
    COFFEE2()
    COFFEE2()
    COFFEE2()
    COFFEE2()
    COFFEE1()
    COFFEE2()
    COFFEE1()


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

}
