package com.laptrinhjavaweb.dto;

public class InvoiceDetailDTO extends AbstractDTO<InvoiceDetailDTO>{
    private int quantity;
    private Long flowerId;
    private Long invoiceId;
    private int totalPrice;
    private FlowerDTO flower;

    public FlowerDTO getFlower() {
        return flower;
    }

    public void setFlower(FlowerDTO flower) {
        this.flower = flower;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Long flowerId) {
        this.flowerId = flowerId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
