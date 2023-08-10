package com.laptrinhjavaweb.dto;

import javax.persistence.Column;

public class InvoiceDTO extends AbstractDTO<InvoiceDTO>{
    private float discount;
    private int amount;
    private Long partnerId;

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }
}
