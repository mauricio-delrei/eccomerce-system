package com.multitone.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = "id")
public class PurchaseOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 659704234369582073L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date_purchase_order;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address delivery_address;

    @OneToMany(mappedBy="id.order")
    private Set<OrderItem> items = new HashSet<>();


    public double getTotalValue() {
        double sum = 0;
        for (OrderItem ip : items) {
            sum += ip.getSubTotal();
        }
        return sum;
    }


    public PurchaseOrder(Long id, Date date_purchase_order, Customer customer, Address delivery_address) {
        super();
        this.id = id;
        this.date_purchase_order = date_purchase_order;
        this.customer = customer;
        this.delivery_address = delivery_address;
    }


}
