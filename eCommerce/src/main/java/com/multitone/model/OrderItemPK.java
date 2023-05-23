package com.multitone.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class OrderItemPK implements Serializable {

    @Serial
    private static final long serialVersionUID = -2296292625149901164L;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private PurchaseOrder order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
