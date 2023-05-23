package com.multitone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = "id")
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 472305194945087654L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address_line_1;
    private String address_line_2;
    private String town_city;
    private String postcode;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Address(Long id, String address_line_1, String address_line_2, String town_city, String postcode) {
        this.id = id;
        this.address_line_1 = address_line_1;
        this.address_line_2 = address_line_2;
        this.town_city = town_city;
        this.postcode = postcode;

    }

    public Address(Long id, String address_line_1, String address_line_2, String town_city, String postcode, Customer customer) {
        this.id = id;
        this.address_line_1 = address_line_1;
        this.address_line_2 = address_line_2;
        this.town_city = town_city;
        this.postcode = postcode;
        this.customer = customer;
    }
}
