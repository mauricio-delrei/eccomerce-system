package com.multitone.service;


import com.multitone.enums.PaymentStatus;
import com.multitone.model.OrderItem;
import com.multitone.model.PaymentBankSlip;
import com.multitone.model.PurchaseOrder;
import com.multitone.repository.OrderItemRepository;
import com.multitone.repository.PaymentRepository;
import com.multitone.repository.PurchaseOrderRepository;
import com.multitone.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final PaymentRepository paymentRepository;

    private final OrderItemRepository orderItemRepository;

    private final PaymentBankSlipService paymentBankSlipService;

    private final ProductService productService;

    private final CustomerService customerService;



    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository,
                                PaymentRepository paymentRepository,
                                OrderItemRepository orderItemRepository,
                                PaymentBankSlipService paymentBankSlipService,
                                ProductService productService,
                                CustomerService customerService
                                ) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.paymentRepository = paymentRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentBankSlipService = paymentBankSlipService;
        this.productService = productService;
        this.customerService = customerService;
    }

    public PurchaseOrder findById(Long id) {
        Optional<PurchaseOrder> orderId = purchaseOrderRepository.findById(id);
        return orderId.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id +
                ", Type: " + PurchaseOrder.class.getName()));
    }

    @Transactional
    public PurchaseOrder addOrder(PurchaseOrder obj){
        obj.setId(null);
        obj.setDate_purchase_order(new Date());
        obj.setCustomer(customerService.findById(obj.getCustomer().getId()));
        obj.getPayment().setStatus(PaymentStatus.PENDING.getCod());
        obj.getPayment().setOrder(obj);
        if (obj.getPayment() instanceof PaymentBankSlip){
            PaymentBankSlip paymentBankSlip = (PaymentBankSlip) obj.getPayment();
            paymentBankSlipService.fillPaymentBankSlip(paymentBankSlip, obj.getDate_purchase_order());
        }
        obj = purchaseOrderRepository.save(obj);
        paymentRepository.save(obj.getPayment());
        for (OrderItem ip: obj.getItems()){
            ip.setDiscount(0.0);
            ip.setProduct(productService.findById(ip.getProduct().getId()));
            ip.setPrice(ip.getProduct().getPrice());
            ip.setPurchaseOrder(obj);
        }
        orderItemRepository.saveAll(obj.getItems());
        return obj;
    }

    public List<PurchaseOrder>getAllOrders(){
        return (List<PurchaseOrder>) purchaseOrderRepository.findAll();
    }


}
