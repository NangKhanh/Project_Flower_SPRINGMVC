package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flower")
public class FlowerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "flower_name")
    private String name;
    @Column(name = "flower_code")
    private String code;
    @Column(name = "flower_price")
    private int price;
    @Column(name = "flower_quantity")
    private int quantity;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "flower_invoice", joinColumns = @JoinColumn(name = "flower_id"),
            inverseJoinColumns = @JoinColumn(name = "invoice_id"))
    private List<InvoiceEntity> invoices = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<InvoiceEntity> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceEntity> invoices) {
        this.invoices = invoices;
    }
}
