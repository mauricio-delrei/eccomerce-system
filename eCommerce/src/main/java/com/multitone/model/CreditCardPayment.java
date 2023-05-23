package com.multitone.model;


import com.fasterxml.jackson.annotation.JsonTypeName;
import com.multitone.enums.PaymentStatus;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.io.Serial;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonTypeName("creditCardPayment")
public class CreditCardPayment extends Payment{

    @Serial
    private static final long serialVersionUID = 9038153687719363197L;


    private Integer number_of_installments;


    public CreditCardPayment(Long id, PaymentStatus paymentStatus, PurchaseOrder purchaseOrder, Integer number_of_installments) {
        super(id,paymentStatus,purchaseOrder);
        this.number_of_installments = number_of_installments;
    }
}
