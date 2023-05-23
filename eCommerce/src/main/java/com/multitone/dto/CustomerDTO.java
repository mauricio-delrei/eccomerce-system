package com.multitone.dto;

import com.multitone.model.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
public class CustomerDTO implements Serializable {

    private Long id;
    @NotEmpty(message = "this field is not must be empty!")
    @Length(min = 5, max = 120, message = "size must be between 5 and 120")
    private String name;
    @NotEmpty(message = "this field is not must be empty!")
    @Email(message = "email is not valid!")
    private String email;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public CustomerDTO(Customer customer) {
        id = customer.getId();
        name = customer.getName();
        email = customer.getEmail();
    }


}
