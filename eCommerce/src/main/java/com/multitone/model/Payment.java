package com.multitone.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.multitone.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = "id")
public class Payment implements Serializable{

    @Serial
    private static final long serialVersionUID = 330663003601593365L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer status;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private PurchaseOrder order;


    public Payment(Long id, PaymentStatus paymentStatus, PurchaseOrder order) {
        this.id = id;
        this.status = (paymentStatus == null) ? null : paymentStatus.getCod();
        this.order = order;
    }

    public PaymentStatus getStatus() {
        return PaymentStatus.toEnum(status);
    }
    public void setStatusPayment(PaymentStatus status) {
        this.status = status.getCod();
    }
}
