package com.multitone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class OrderItem implements Serializable {

    @Serial
    private static final long serialVersionUID = -5564412853467369801L;

    @JsonIgnore
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();
    private Double discount;
    private Integer quantity;
    private Double price;


    public OrderItem() {
    }



    public OrderItem(PurchaseOrder order, Product product, Double discount, Integer quantity, Double price) {
        super();
        id.setOrder(order);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;


    }

    public double getSubTotal(){
        return (price - discount) * quantity;
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        id.setProduct(product);
    }

    @JsonIgnore
    public PurchaseOrder getPurchaseOrder(){
        return id.getOrder();
    }
    public void setPurchaseOrder(PurchaseOrder order){
        id.setOrder(order);
    }

}
