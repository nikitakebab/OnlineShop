package org.example.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.model.Customer;

@Data
@NoArgsConstructor
public class CustomerDTO {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public CustomerDTO(@NonNull Customer customer){
        this.customerId = customer.getCustomerId();
        this.address = customer.getAddress();
        this.email = customer.getEmail();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.phone = customer.getPhone();
    }
}
