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
    private Integer customer_id;
    private String user_name;
    private String pword;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;

    public Customer() {

    }
    
    public Customer (String user_name, String pword, String first_name, String last_name, String email, String phone){
        this.user_name = user_name;
        this.pword = pword;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
    }
}
