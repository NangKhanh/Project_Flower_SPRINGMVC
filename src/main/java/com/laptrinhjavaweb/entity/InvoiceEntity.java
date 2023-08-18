package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "discount")
    private float discount;
    @Column(name = "amount")
    private int amount;
    @Column(name = "total_cost")
    private int totalCost;
    @Column(name = "status")
    private int status;

    @ManyToMany(mappedBy = "invoices")
    private List<FlowerEntity> flowers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    private PartnerEntity partner;

    public Long getId() {
        return id;
    }

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

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public List<FlowerEntity> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<FlowerEntity> flowers) {
        this.flowers = flowers;
    }

    public PartnerEntity getPartner() {
        return partner;
    }

    public void setPartner(PartnerEntity partner) {
        this.partner = partner;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
