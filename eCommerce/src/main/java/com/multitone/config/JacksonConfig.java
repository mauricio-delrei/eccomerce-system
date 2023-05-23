package com.multitone.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multitone.model.CreditCardPayment;
import com.multitone.model.PaymentBankSlip;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder(){
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder(){
            public void configure(ObjectMapper objectMapper){
                objectMapper.registerSubtypes(CreditCardPayment.class);
                objectMapper.registerSubtypes(PaymentBankSlip.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}
