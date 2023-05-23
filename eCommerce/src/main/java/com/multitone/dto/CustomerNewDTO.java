package com.multitone.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerNewDTO implements Serializable {


    @NotEmpty(message = "this field is not must be empty!")
    @Length(min = 5, max = 120, message = "size must be between 5 and 120")
    private String name;

    @NotEmpty(message = "this field is not must be empty!")
    @Email(message = "email is not valid!")
    private String email;

    @NotEmpty(message = "this field is not must be empty!")
    private String address_line_1;

    @NotEmpty(message = "this field is not must be empty!")
    private String address_line_2;

    private String town_city;

    @NotEmpty(message = "this field is not must be empty!")
    private String postcode;

    @NotEmpty(message = "this field is not must be empty!")
    private String mobile;

    private String phone;

}
