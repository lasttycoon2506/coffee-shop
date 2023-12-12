module coffeeshop {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive java.sql;
    requires transitive org.hibernate.orm.core;
    requires transitive java.naming;
    requires transitive jakarta.persistence;
    requires bcrypt;
    

    exports coffeeshop;
    opens coffeeshop to at.favre.lib.crypto.bcrypt;
    opens coffeeshop.Controllers to javafx.fxml, org.hibernate.orm.core;
    exports coffeeshop.Controllers;
    opens coffeeshop.Entities.Customers to org.hibernate.orm.core;
}



