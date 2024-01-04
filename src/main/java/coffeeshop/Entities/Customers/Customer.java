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


@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "user_name", unique = true)
    private String userName;
    private String pword;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;

    public Customer(){
    }
    public Customer (String userName, String pword, String firstName, String lastName, String email, String phone) throws NoSuchAlgorithmException, InvalidKeySpecException{
        this.userName = userName;
        this.pword = PasswordGen.getHashedPw(pword);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public int getCustomerID(){
        return customerId;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return pword;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }

    public void setUserName(String newUserName){
        this.userName = newUserName;
    }
    public void setPassword(String newPW){
        this.pword = newPW;
    }
    public void setFirstName(String newFirstName){
        this.firstName = newFirstName;
    }
    public void setLastName(String newLastName){
        this.lastName = newLastName;
    }
    public void setEmail(String newEmail){
        this.email = newEmail;
    }
    public void setPhone(String newPhone){
        this.phone = newPhone;
    }
}
