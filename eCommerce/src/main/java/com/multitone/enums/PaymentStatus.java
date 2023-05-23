package com.multitone.enums;

public enum PaymentStatus {

    PENDING(1, "Pending"),
    COMPLETED(2, "Completed"),
    CANCELED(3, "Canceled");

    private final Integer cod;
    private final String description;

    PaymentStatus(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String get() {
        return description;
    }

    public static PaymentStatus toEnum(Integer cod){
        if (cod == null) {
            return null;
        }

        for (PaymentStatus paymentStatus: PaymentStatus.values()){
            if (cod.equals(paymentStatus.getCod())){
                return paymentStatus;
            }
        }

        throw new IllegalArgumentException("Value is not invalid! "+ cod);
    }
}

