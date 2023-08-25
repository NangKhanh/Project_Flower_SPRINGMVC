package com.laptrinhjavaweb.dto;

import java.util.List;

public class InvoiceDTO extends AbstractDTO<InvoiceDTO>{
    private float discount;
    private int totalCost;
    private int status;
    private Long partnerId;
    private List<FlowerDTO> flowers;
    private List<InvoiceDetailDTO> invoiceDetails;

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public List<FlowerDTO> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<FlowerDTO> flowers) {
        this.flowers = flowers;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<InvoiceDetailDTO> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetailDTO> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
