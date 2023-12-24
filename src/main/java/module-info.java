module coffeeshop {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive java.sql;
    requires transitive org.hibernate.orm.core;
    requires transitive java.naming;
    requires transitive jakarta.persistence;
    requires bcrypt;
    
    exports coffeeshop;
    opens coffeeshop.Controllers to javafx.fxml, org.hibernate.orm.core;
    opens coffeeshop.Entities.Customers to org.hibernate.orm.core, javafx.base;
    opens coffeeshop.Controllers.Customers to javafx.fxml, org.hibernate.orm.core;
    opens coffeeshop.Entities.Coffee to org.hibernate.orm.core, javafx.base;
}



