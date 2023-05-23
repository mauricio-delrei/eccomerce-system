package com.multitone.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.multitone.enums.PaymentStatus;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonTypeName("paymentBankSlip")
public class PaymentBankSlip extends Payment{

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date payment_date;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date due_date;

    public PaymentBankSlip(Long id, PaymentStatus paymentStatus, PurchaseOrder order, Date due_date, Date payment_date) {
    super(id,paymentStatus,order);
    this.due_date = due_date;
    this.payment_date = payment_date;
    }

}
