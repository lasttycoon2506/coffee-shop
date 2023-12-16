package coffeeshop.Entities.Customers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import coffeeshop.Security.Password;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Customers")
public class Customer {
    public Customer(){
    }
    
    public Customer (String user_name, String pword, String first_name, String last_name, String email, String phone) throws NoSuchAlgorithmException, InvalidKeySpecException{
        this.user_name = user_name;
        this.pword = Password.getHashedPw(pword);
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customer_id;

    @Column(unique = true)
    private String user_name;

    private String pword;

    private String first_name;

    private String last_name;

    @Column(unique = true)
    private String email;
    
    @Column(unique = true)
    private String phone;
}
