package com.multitone.service;


import com.multitone.model.PaymentBankSlip;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class PaymentBankSlipService {

    public void fillPaymentBankSlip(PaymentBankSlip payment, Date requestedMoment){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(requestedMoment);
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        payment.setPayment_date(calendar.getTime());
    }
}
