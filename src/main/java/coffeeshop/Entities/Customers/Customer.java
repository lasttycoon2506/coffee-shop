package coffeeshop.Entities.Customers;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String user;
    private String password;
    private String fname;
    private String lname;
    private String email;
    private String phone;

    public Customer (String user, String password, String fname, String lname, String email, String phone){
        this.user = user;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
    }
}
