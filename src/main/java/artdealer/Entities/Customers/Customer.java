package artdealer.Entities.Customers;

@Entity
@Table(name = "Customers")
public class Customer {
    private String user;
    private String password;
    private String fname;
    private String lname;
    private String email;
    private String phone;
}
