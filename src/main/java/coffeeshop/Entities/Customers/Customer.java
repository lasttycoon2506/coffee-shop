package coffeeshop.Entities.Customers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import coffeeshop.Security.PasswordGen;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


//@ for JPA access
@Entity
@Table(name = "Customers")
public class Customer {
    //@ = JPA interfacing / customer_id handled by mysql
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;
    @Column(unique = true)
    private String user_name;
    private String pword;
    private String first_name;
    private String last_name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;

    public Customer(){
    }
    public Customer (String user_name, String pword, String first_name, String last_name, String email, String phone) throws NoSuchAlgorithmException, InvalidKeySpecException{
        this.user_name = user_name;
        this.pword = PasswordGen.getHashedPw(pword);
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
    }

    public int getCustomerID(){
        return customer_id;
    }
    public String getUserName(){
        return user_name;
    }
    public String getPassword(){
        return pword;
    }
    public String getFirstName(){
        return first_name;
    }
    public String getLastName(){
        return last_name;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }

    public void setUserName(String newUserName){
        this.user_name = newUserName;
    }
    public void setPassword(String newPW){
        this.pword = newPW;
    }
    public void setFirstName(String newFirstName){
        this.first_name = newFirstName;
    }
    public void setLastName(String newLastName){
        this.last_name = newLastName;
    }
    public void setEmail(String newEmail){
        this.email = newEmail;
    }
    public void setPhone(String newPhone){
        this.phone = newPhone;
    }
}
