package coffeeshop.Models;

public record CustomerDTO (
    String userName,
    String password,
    String firstName, 
    String lastName, 
    String email,
    String phone
)
    {
}
